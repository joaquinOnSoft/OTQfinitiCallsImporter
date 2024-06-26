# OTQfinitCallsImporter

Utility to generate a .xls file to be used as input in the **OpenText Qfiniti Data Importer**, from a folder structure provided by a client. It usually contains an Excel, CSV or JSON file generated by a 3rd party software like **Verint**, **Genesys** or **NICE**.

> **NOTE**: It assumes an input folder structure that contains an excel file (.xls) with the metadata associated with the call recording files.  

# OTQfinitCallsImporter Overview
Usually **3rd party software** like Genesys, NICE or Verint exports an Excel, CSV or JSON file with the metadata associated with each call recording. We call **INPUT file** to this file.

**OpenText Qfiniti Data Importer** requires and Excel file  with the metadata associated with each call recording. We call **OUTPUT file** to this file. 

**OTQfinitCallsImporter** requires a **JSON configuration file** that defines the mapping between the input fields and the output field. Not all the fields should be mapped, some of them are populated with a default value, an automatically generated value, or simply ignored.


```
   MAPPING between 3rd party output excel file and  OpenText Qfiniti Data Importer excel input file
   	
   +--------------------+                                +-----------------------+
   | Excel generated    |                                | Excel generated       |
   |       by           | ------------------------------>|       by              |
   | 3rd party software |     OTQfinitCallsImporter      | OTQfinitCallsImporter |
   +--------------------+                                +-----------------------+
                                       /\   
                                        |
                                        |
                                        |
                              +--------------------+ 
                              |  JSON config file  |
                              |                    | 
                              |   Fields mapping   | 
                              +--------------------+                                      
```

On the other hand, other clients just provide a folder which contains call recordings in .wav format. In this scenario  **OTQfinitCallsImporter** can generate the **OUTPUT file** based on the metadata contained in each .wav file. In case we are working in a Prove of Concept (POC), it can generated some metadata randomly for each call, like ANI, DNIS, Team Member or Group.

```
   Folder with call recordings used as input to generate the excel file required as input by 
   OpenText Qfiniti Data Importer
    __________
   /           \________                                 +-----------------------+
   | Call recordings    |                                | Excel generated       |
   |       in           | ------------------------------>|       by              |
   | .wav format        |     OTQfinitCallsImporter      | OTQfinitCallsImporter |
   +--------------------+                                +-----------------------+
      |                                 /\   
      +- call01.wav                     |
      +- call01.wav                     |
      +- call01.wav                     |
      + ...                             |
      +- callNN.wav                     |
                                        |
                                        |
                              +--------------------+ 
                              |  JSON config file  |
                              |                    | 
                              |   Fields mapping   | 
                              +--------------------+                                      
```

> The output file generated by **OTQfinitCallsImporter** will be used as input by **OpenText Qfiniti Data Importer**, 
> so **OTQfinitCallsImporter** is a format conversion tool, not an imported by itself. 


## Command line execution 

This utility is distributed as a runnable .jar file.

These are the accepted parameters:

usage: `java -jar OTQfinitiCallsImporter-23.02.14.jar`
 * **-a**, **--allaudio** 			(Optional) Process ALL audio formats (.wav, .gsm, .mp3, .ogg). By default only .wav is processed 	
 * **-c**, **--config <arg>**		(Required) JSON config name which defines the field mapping between the 3rd party metadata file and the .xls input file required by **OpenText Qfiniti Data Importer**
 * **-f**, **--ffmpeg-path <arg>**	(Optional) Path to `ffprobe` executable, a *ffmpeg* command line utility used to read metadata from audio files.
 * **-o**, **--output <arg>**		(Optional) Output file name. 'calls.xlsx' by default
 * **-p**, **--path <arg>**			(Required) UNC Path to the call recordings files (.wav files)


> **NOTE:** You can define `FFMPEG_BIN` as a system property or an environment variable in
> order to provide the path to `ffprobe` executable instead of using `--ffmpeg-path` option.

### Example of invocation

```
$ java -jar OTQfinitiCallsImporter-23.02.14.jar -c client_i_mapping.json -o call_recordinds.xls -p "\\WIN-FH7JGOHVC21\Recordings"
```

### Output

Example of Excel file generated by this utility:

```
$ java -jar OTQfinitiCallsImporter-23.02.14.jar -c client_i_mapping.json -o call_recordinds.xls -p "\\WIN-FH7JGOHVC21\Recordings"
```

| Path_Name                  | Name                      | Date_time           | duration      |
| -------------------------- | ------------------------- | ------------------- | ------------- | 
| \\\\MY_SERVER\\recordings  | file_example_WAV_1MG.wav  | 11/07/2019 23:15:43 | 33            |
| \\\\MY_SERVER\\recordings  | file_example_WAV_2MG.wav  | 11/07/2019 23:15:43 | 29            |         

## JSON mapping configuration file 

The application requires a JSON configuration file as input. There are two main types of configuration files:
   - Mapping: Used to define the mapping between the input file and the output file
   - Filler: Used when there is not an input file, just the call recordings


There are 3 main properties:
   - **inputType**: Input type. Possible values:
      - **xls**: Excel file
      - **NoMetadata**: No input file with metadata available. When we use this input type **the 'fielMapping' field must be an empty array**.
      - **csv**: CSV file
      - **json**: JSON file      
   - **fieldFiller**: List of field fillers which provide a default or an automatically generated value for a given field. These are the possible values of a field filler:
      - **oname**: (Required) Output field name
      - **ovalue**: (Optional) Output default value
      - **filler**: (Optional) Class that extends **com.opentext.qfiniti.importer.io.filler.AbstractFiller** and provides an automatically generated value for this field.
      > **NOTE**: 'ovalue' field will be ignored when 'filler' property is present.
   - **fielMapping**: List of field mappings which provide a mapping between the input field and the output field. These are the possible values:
      - **iname**: (Required) Input field name
      - **itype**: Input data type. Default value "string"
      - **mapped**: Flag to indicate if the input file is exported to the ouput file
      - **oname**: (Required) Output field name
      - **otype**: Output data type. Default value "string"
      - **transformer**: (Optional) Class that implements the interface **com.opentext.qfiniti.importer.io.transformer.ITransformer** and provides some kind of processing/data manipulation for this field.


For further details about the JSON mapping configuration file, please visit these pages:

 - [Valid output field names](doc/valid-output-field-names.md)
 - [Available field fillers](doc/available-field-fillers.md)
 - [Available field transformers](doc/available-field-transformers.md)


## Tips & tricks

Finally, you can also consult some [tips on how to run Qfiniti Call Importer](doc/tips-and-tricks.md), once you have already generated the excel with the metadata.

