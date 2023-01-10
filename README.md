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

The output file generated by **OTQfinitCallsImporter** will be used as input by **OpenText Qfiniti Data Importer**, so **OTQfinitCallsImporter** is a format conversion tool, not an imported by itself. 


## Command line execution 

This utility is distributed as a runnable .jar file.

These are the accepted parameters:

usage: `java -jar OTQfinitiCallsImporter-23.01.10.jar`
 * **-a**, **--allaudio** 			(Optional) Process ALL audio formats (.wav, .gsm, .mp3, .ogg). By default only .wav is processed 	
 * **-c**, **--config <arg>**		(Required) JSON config name which defines the field mapping between the 3rd party metadata file and the .xls input file required by **OpenText Qfiniti Data Importer**
 * **-o**, **--output <arg>**		(Optional) Output file name. 'calls.xlsx' by default
 * **-p**, **--path <arg>**			(Required) UNC Path to the call recordings files (.wav files) 


### Example of invocation

```
$ java -jar OTQfinitiCallsImporter-23.01.10.jar -c client_i_mapping.json -o call_recordinds.xls -p "\\WIN-FH7JGOHVC21\Recordings"
```

### Output

Example of Excel file generated by this utility:

```
$ java -jar OTQfinitiCallsImporter-23.01.10.jar -c client_i_mapping.json -o call_recordinds.xls -p "\\WIN-FH7JGOHVC21\Recordings"
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


### Valid output field names
These are the valid output field names admitted by  **OpenText Qfiniti Data Importer**

   - **Path_Name**: **UNC path.**
   
> **WARNING**:  **UNC** (Universal Naming Convention) is a standard for identifying servers, printers and other resources in a network.
> Qfiniti Data Importer requires a UNC path to work properly 

   - **Date_Time**: Recording date & time in format 'dd/MM/yyyy HH:mm:ss'
   - **Team_Member_Name**: Team member name in format:
      &lt;1st FAMILY NAME&gt; &lt;2nd FAMILY NAME&gt;, &lt;NAME&gt;
      or
      &lt;1st FAMILY NAME&gt;, &lt;NAME&gt;
   - **duration**: Call recording duration in seconds
   - **group_hierarchy**: Group hierarchy (Group name)
   - **dnis**: Dialed Number Identification Service (DNIS) is a service sold by telecommunications companies to corporate clients that identifies the originally dialed telephone number of an inbound call. The client may use this information for call routing to internal destinations or activation of special call handling.
   - **ani**: (Automatic Number Identification) ANI information is used like caller ID except for a few differences that make it impossible to block or hide.	
   - **File_Name**: Call recording file name (usually a .wav file)
 
 
### Available field fillers

Fillers are classes that extends com.opentext.qfiniti.importer.io.filler.AbstractFiller and provides an automatically generated value for a given field, or extract some metadata from the .wav file.

#### com.opentext.qfiniti.importer.io.filler.AniFiller
Generate a random ANI (Client phone number)

#### com.opentext.qfiniti.importer.io.filler.DateFromFileFiller
Recover the creation date and time from the .wav file

#### com.opentext.qfiniti.importer.io.filler.DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller
Recover the creation date and time from the .wav file name. The file name MUST match this pattern:

> ExtXXXXX_MM_dd_yyyy_HH;mm;ss

Some examples:
   - ext42094_01_14_2016_16;29;15.wav
   - ext42098_03_30_2015_11;35;46.wav
   - ext42110_02_16_2017_15;39;00.wav

#### com.opentext.qfiniti.importer.io.filler.DnisFiller
Generate a random DNIS (Call type)

#### com.opentext.qfiniti.importer.io.filler.DurationFromMetadataFiller
Recover the duration in seconds from the .wav file

#### com.opentext.qfiniti.importer.io.filler.ExtendedFields2UserDataFiller
Generate "user_data" field based on extended fields values

> user_data/UUData for the transaction. These are **key=value** pairs, separated by the delimiter. For example 2#;zip=54321#;accnt=123456789#;

#### com.opentext.qfiniti.importer.io.filler.FileNameFromFileFiller
Recover the file name from the .wav file

#### com.opentext.qfiniti.importer.io.filler.GroupHierarchyDolceVitaFiller
Generate a random Group Hierarchy name that match this pattern: VS-TI-FL-Team<XX>

#### com.opentext.qfiniti.importer.io.filler.GroupHierarchyFiller
Generate a fix Group Hierarchy name: "Client-i"

#### com.opentext.qfiniti.importer.io.filler.TeamMemberFiller
Generate a Team Member Name selected randomly from a predefined set of user names 

#### com.opentext.qfiniti.importer.io.filler.TeamMemberFromSGTeamFiller
Generate a team member name from the properties "SG" and "Team"

#### com.opentext.qfiniti.importer.io.filler.TeamMemberFromTeamFiller
Generate a team member name from the properties "Team", i.e. the 'Team' 
value 'VS-TI-FL-Team36' will generate a Team Member name 'VS-TI-FL-Team36, agent36'

#### Planeta Naming Convention fillers

Planeta use a naming convention in their audio files. The file names have the following structure separated by hyphens:

 * **ID**: call identifier
 * **Date**: call date, format matches this pattern `ddmmaaaa_hhmmss`
 * **Service**:
 * **Agent**: Agent identifier
 * **Duration**: duration in seconds
 * **Telephone**: client call number. The first 4 digits correspond to a route to be ignored for processing.

File name example:

```
13032476-31122021_143358-2622-5631-00307-0204678999999.gsm
```

Where:

 * **ID**: 13032476
 * **Date**: 31122021_143358
 * **Service**: 2622
 * **Agent**: 5631
 * **Duration**: 00307
 * **Route**: 0204
 * **Telephone**: 678999999
 
##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionAgentFiller

Extract the agent ID from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionDateFiller

Extract the (call) date from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionDurationFiller

Extract the duration, in seconds, from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionIdFiller

Extract the call ID from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionRouteFiller

Extract the `route` (ANI) from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionServiceFiller

Extract the service ID from an audio file that follows the Planeta naming convention.

##### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionTelephoneFiller

Extract the telephone (DNIS) from an audio file that follows the Planeta naming convention.


### Available field transformers

Transformers are classes that implements the interface **com.opentext.qfiniti.importer.io.transformer.ITransformer** and provides some kind of processing/data manipulation for a given field.

#### com.opentext.qfiniti.importer.io.transformer.CallDirectionTransformer
Transform a text literal ('Inbound' or 'Outbound') into a integer to indicate the 'call direction':
 - 0: Unknown
 - 1: Inbound
 - 2: Outbound

#### com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformer
Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy HH:mm:ss'

#### com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformer
Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy HH:mm:ss'

#### com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmTransformer
Transforms a date from 'MM/dd/yyyy HH:mm' to format 'dd/MM/yyyy HH:mm:ss'

#### com.opentext.qfiniti.importer.io.transformer.DateyyyyMMddTHHmmssSSSTransformer
Transforms a date from "yyyy-MM-dd'T'HH:mm:ss.SSS'+'0000" to format 'dd/MM/yyyy HH:mm:ss'

#### com.opentext.qfiniti.importer.io.transformer.DurationMsecToSecTransformer
Convert call duration from milliseconds to seconds

#### com.opentext.qfiniti.importer.io.transformer.ExtensionPrefix2FileNameCachedTransformer
Generates a call recording file name from a prefix, e.g. from and prefix like 
**ext2960006643_05_04_2021_12;40;57** will search a file in the provided path that match
the given prefix:

#### com.opentext.qfiniti.importer.io.transformer.ExtensionPrefix2FileNameTransformer
Generates a call recording file name from a prefix, e.g. from and prefix like 
**ext2960006643_05_04_2021_12;40;57** will search a file in the provided path that match
the given prefix:

   `ext2960006643_05_04_2021_12;40;57_18638_it1483yw.wav`
   
> NOTE: This transformer has been deprecated since release 21.3. Please use `ExtensionPrefix2FileNameCachedTransformer` in his place

#### com.opentext.qfiniti.importer.io.transformer.HoursMinutesSecondsToSecondsTransformer
Transforms a duration expressed as hh:mm:ss to a number of seconds

#### com.opentext.qfiniti.importer.io.transformer.InteractionIdKey2FileNameTransformer
Generates a call recording file name from an id, e.g. from and Id like 2001788444D0191021 will generate a file name like:

   IRCall_2001788444D0191021.wav
   
#### com.opentext.qfiniti.importer.io.transformer.LocalPartyName2TeamMemberNameTransformer
Transforms a team member name from one of these formats:

   - 1st_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
   - 1st_NAME 2nd_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
   - 1st_NAME 1st_FAMILY_NAME

to one of these:

   - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 
   - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 2nd_NAME
   - 1st_FAMILY_NAME, 1st_NAME 

#### com.opentext.qfiniti.importer.io.transformer.RecordingId2FileNameTransformer
Recording Identifier to call recording .wav file name

#### com.opentext.qfiniti.importer.io.transformer.RemoveHyphensTransformer
Remove Hyphens characters '-' from the input

#### com.opentext.qfiniti.importer.io.transformer.UserId2UserFullNameTransformer
Transform a user id to a user full name: Last, First Middle

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```

#### com.opentext.qfiniti.importer.io.transformer.UserId2UserNameTransformer
Transform a user id to a user surname.

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```

#### com.opentext.qfiniti.importer.io.transformer.UserId2UserSurnameTransformer
Transform a user id to a user surname.

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```

### Configuration file examples

Let'see some examples:

#### Mapping JSON mapping configuration file

```json
{
	"inputType": "xls",
	"fieldFiller": [
		{
			"oname": "Path_Name",
			"ovalue": "C:\\Users\\jgarzonpena\\eclipse-workspace\\OTQfinitiCallsImporter\\src\\test\\resources\\client-i\\samples"
		},		
		{
			"oname": "group_hierarchy",
			"filler": "com.opentext.qfiniti.importer.io.filler.GroupHierarchyFiller"
		}		
	],
	"fieldMapping": [
		{
			"iname": "Media Type",
			"itype": "String",
			"mapped": "false"
		},
		{
			"iname": "Recording Type",
			"itype": "String",
			"mapped": "false"
		},
		{
			"iname": "Recording ID",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Date/Time",
			"itype": "String",		 
			"mapped": "true",
			"oname": "Date_Time",
			"otype": "String",
			"transformer": "com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformer"
		},
		{
			"iname": "Recording Length",
			"itype": "String",
			"mapped": "true",
			"oname": "duration",
			"otype": "String",
			"transformer": "com.opentext.qfiniti.importer.io.transformer.HoursMinutesSecondsToSecondsTransformer"			
		},
		{
			"iname": "Direction",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Initiator Interaction Address",
			"itype": "string",
			"mapped": "true",
			"oname": "ANI",
			"otype": "String" 
		},
		{
			"iname": "Interaction Address",
			"itype": "string",
			"mapped": "true",
			"oname": "dnis",
			"otype": "String"  
		},
		{
			"iname": "Local Party Name",
			"itype": "string",
			"mapped": "true",
			"oname": "Team_Member_Name",
			"otype": "String",
			"transformer": "com.opentext.qfiniti.importer.io.transformer.LocalPartyName2TeamMemberNameTransformer"					
		},
		{
			"iname": "Scoring User",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Scoring Status",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Queue",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Interaction ID Key",
			"itype": "string",
			"mapped": "true",
			"oname": "File_Name",
			"otype": "String",
			"transformer": "com.opentext.qfiniti.importer.io.transformer.InteractionIdKey2FileNameTransformer"								  
		},
		{
			"iname": "Initiation Policy",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Related Recordings",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Customer Keyword Score",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Agent Keyword Score",
			"itype": "string",
			"mapped": "false"  
		},
		{
			"iname": "Total Keyword Score",
			"itype": "string",
			"mapped": "false"  
		}		
	]
}
```  

#### Filler JSON mapping configuration file

```json
{
	"inputType": "NoMetadata",
	"fieldFiller": [
		{
			"oname": "Path_Name",
			"ovalue": "C:\\Users\\jgarzonpena\\eclipse-workspace\\OTQfinitiCallsImporter\\src\\test\\resources\\client-o"
		},	
		{
			"oname": "File_Name",
			"filler": "com.opentext.qfiniti.importer.io.filler.FileNameFromFileFiller"
		},		
		{
			"oname": "Date_Time",
			"filler": "com.opentext.qfiniti.importer.io.filler.DateFromFileFiller"
		},			
		{
			"oname": "duration",
			"filler": "com.opentext.qfiniti.importer.io.filler.DurationFromMetadataFiller"
		},
		{
			"oname": "group_hierarchy",
			"filler": "com.opentext.qfiniti.importer.io.filler.GroupHierarchyFiller"
		},		
		{
			"oname": "Team_Member_Name",
			"filler": "com.opentext.qfiniti.importer.io.filler.TeamMemberFiller"
		},		
		{
			"oname": "dnis",
			"filler": "com.opentext.qfiniti.importer.io.filler.DnisFiller"
		},
		{
			"oname": "ani",
			"filler": "com.opentext.qfiniti.importer.io.filler.AniFiller"
		}				
	],
	"fieldMapping": []
} 
```

> **NOTE**: fielMapping field is an empty array. In this scenario there is no input file, so, we don't need to define mapping between input and output file.

## Tips & tricks

### Qfiniti Call Importer
The **Qfiniti Data Import tool** is used to import recording transactions from etalk Recorder, older versions of Qfiniti, or a 3rd party recording device into Qfiniti. It can also be used to add teams and team members, especially ones associated with those transactions, if those teams and or team members do not already exist in HP Qfiniti.

#### Executing Qfiniti Call Importer
The importer can be run as a service or by command line. When run on command line, it is invoked as follows to run one time, immediately.

```
	QfinitiDataImport.exe /runonce
```

#### Creating user during the import process
**Qfiniti Data Import** can create users and groups during the import process, you just need to set as `true` the fields `CreateUsers` and `CreateGroups` in the **QfinitiDataImport.exe.config** file:

```xml
	<plugin type="UserLookupPlugin">
		<CacheUsers>true</CacheUsers>
		<CreateUsers>true</CreateUsers>
		<CreateGroups>true</CreateGroups>
		<LoginType>0</LoginType>
		<LoginFormat>
			<field select="login_id" />
		</LoginFormat>
	</plugin>
```

#### Assign licenses to users on Qfiniti
If you have created users/groups during the import process you must review `Qfiniti > Teams > Organization` to check if each new user has a transcription license assigned:

![Qfiniti - Teams - Organization](img/qfiniti-teams-organization.png)


![Qfiniti - Teams - Organization - Edit user](img/qfiniti-edit-user-popup.png)


