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

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * SEE: JUnit 5 Test Suites
 * https://howtodoinjava.com/junit5/junit5-test-suites-examples/ 
 **/
@Suite
@SelectPackages({
	"com.opentext.qfiniti.importer",
	"com.opentext.qfiniti.importer.io",
	"com.opentext.qfiniti.importer.io.filler",
	"com.opentext.qfiniti.importer.io.filler.planeta",
	"com.opentext.qfiniti.importer.io.metadata",
	"com.opentext.qfiniti.importer.io.transformer",
	"com.opentext.qfiniti.importer.pojo",
	"com.opentext.qfiniti.importer.util",	
	})
@SuiteDisplayName("OTQfinitCallsImporter Suite Demo")
public class QfinitiTestSuite {

}
