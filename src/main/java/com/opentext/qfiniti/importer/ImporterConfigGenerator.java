/*
 *   (C) Copyright 2019 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ImporterConfigGenerator {
	private static final String DEFAULT_OUTPUT_FILE = "calls.xls";

	/**
	 * SEE: How to parse command line arguments in Java? [closed]
	 * http://stackoverflow.com/questions/367706/how-to-parse-command-line-arguments-in-java
	 * SEE: Commons CLI. The Apache Commons CLI library provides an API for parsing
	 * command line options passed to programs. http://commons.apache.org/cli/
	 * 
	 * @param args - Arguments received from the command line.
	 */
	public static void main(String args[]) {
		Options options = new Options();

		Option agentOpt = new Option("a", "agent", false, "Add agent info");
		options.addOption(agentOpt);

		Option flattenOpt = new Option("f", "flatten", true,
				"Flatten Iberdrola folder estructure in the destiny folder. OTHER PARAMETERS WILL IGNORED except 'p' or 'path'");
		options.addOption(flattenOpt);

		Option outputOpt = new Option("o", "output", true, "Output file name. 'calls.xls' by default");
		options.addOption(outputOpt);

		Option pathOpt = new Option("p", "path", true, "UNC Path to the call recordings files");
		pathOpt.setRequired(true);
		options.addOption(pathOpt);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			formatter.printHelp("ImporterConfigGenerator", options);

			System.exit(1);
			return;
		}

		String path = cmd.getOptionValue("path");

		String output = cmd.getOptionValue("output");
		if (output == null) {
			output = DEFAULT_OUTPUT_FILE;
		}

		boolean agent = cmd.hasOption("agent");

		QfinitiICG configGenerator = new QfinitiICG(path);
		configGenerator.setAgent(agent);
		configGenerator.setOutput(output);

		try {
			configGenerator.generate();
		} catch (IOException e) {
			System.err.print("Error accessing : " + e.getMessage());
		} catch (InvalidFormatException e) {
			System.err.print("Invalid metadata value: " + e.getMessage());
		}

	}
}
