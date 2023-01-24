# Available field fillers

Fillers are classes that extends com.opentext.qfiniti.importer.io.filler.AbstractFiller and provides an automatically generated value for a given field, or extract some metadata from the .wav file.

## Generic fillers

### com.opentext.qfiniti.importer.io.filler.AniFiller
Generate a random ANI (Client phone number)

### com.opentext.qfiniti.importer.io.filler.DateFromFileFiller
Recover the creation date and time from the .wav file

### com.opentext.qfiniti.importer.io.filler.DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller
Recover the creation date and time from the .wav file name. The file name MUST match this pattern:

> ExtXXXXX_MM_dd_yyyy_HH;mm;ss

Some examples:
   - ext42094_01_14_2016_16;29;15.wav
   - ext42098_03_30_2015_11;35;46.wav
   - ext42110_02_16_2017_15;39;00.wav

### com.opentext.qfiniti.importer.io.filler.DnisFiller
Generate a random DNIS (Call type)

### com.opentext.qfiniti.importer.io.filler.DurationFromMetadataFiller
Recover the duration in seconds from the .wav file

### com.opentext.qfiniti.importer.io.filler.ExtendedFields2UserDataFiller
Generate "user_data" field based on extended fields values

> user_data/UUData for the transaction. These are **key=value** pairs, separated by the delimiter. For example 2#;zip=54321#;accnt=123456789#;

### com.opentext.qfiniti.importer.io.filler.FileNameFromFileFiller
Recover the file name from the .wav file

### com.opentext.qfiniti.importer.io.filler.GroupHierarchyDolceVitaFiller
Generate a random Group Hierarchy name that match this pattern: VS-TI-FL-Team<XX>

### com.opentext.qfiniti.importer.io.filler.GroupHierarchyFiller
Generate a fix Group Hierarchy name: "Client-i"

### com.opentext.qfiniti.importer.io.filler.PathNameFromFileFiller
Recover the file parent directory from the audio file (usually a .wav file)

### com.opentext.qfiniti.importer.io.filler.TeamMemberFiller
Generate a Team Member Name selected randomly from a predefined set of user names 

### com.opentext.qfiniti.importer.io.filler.TeamMemberFromSGTeamFiller
Generate a team member name from the properties "SG" and "Team"

### com.opentext.qfiniti.importer.io.filler.TeamMemberFromTeamFiller
Generate a team member name from the properties "Team", i.e. the 'Team' 
value 'VS-TI-FL-Team36' will generate a Team Member name 'VS-TI-FL-Team36, agent36'

## Planeta Naming Convention fillers

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
 
### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionAgentFiller

Extract the agent ID from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionDateFiller

Extract the (call) date from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionDurationFiller

Extract the duration, in seconds, from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionIdFiller

Extract the call ID from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionRouteFiller

Extract the `route` (ANI) from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionServiceFiller

Extract the service ID from an audio file that follows the Planeta naming convention.

### com.opentext.qfiniti.importer.io.filler.planeta.PlanetaNamingConventionTelephoneFiller

Extract the telephone (DNIS) from an audio file that follows the Planeta naming convention.
