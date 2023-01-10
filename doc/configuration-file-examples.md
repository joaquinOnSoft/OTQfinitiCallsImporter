# Configuration file examples

Let'see some examples:

## Mapping JSON mapping configuration file

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

## Filler JSON mapping configuration file

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