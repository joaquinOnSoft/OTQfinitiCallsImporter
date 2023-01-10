# Available field transformers

Transformers are classes that implements the interface **com.opentext.qfiniti.importer.io.transformer.ITransformer** and provides some kind of processing/data manipulation for a given field.

## com.opentext.qfiniti.importer.io.transformer.CallDirectionTransformer
Transform a text literal ('Inbound' or 'Outbound') into a integer to indicate the 'call direction':
 - 0: Unknown
 - 1: Inbound
 - 2: Outbound

## com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformer
Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy HH:mm:ss'

## com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformer
Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy HH:mm:ss'

## com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmTransformer
Transforms a date from 'MM/dd/yyyy HH:mm' to format 'dd/MM/yyyy HH:mm:ss'

## com.opentext.qfiniti.importer.io.transformer.DateyyyyMMddTHHmmssSSSTransformer
Transforms a date from "yyyy-MM-dd'T'HH:mm:ss.SSS'+'0000" to format 'dd/MM/yyyy HH:mm:ss'

## com.opentext.qfiniti.importer.io.transformer.DurationMsecToSecTransformer
Convert call duration from milliseconds to seconds

## com.opentext.qfiniti.importer.io.transformer.ExtensionPrefix2FileNameCachedTransformer
Generates a call recording file name from a prefix, e.g. from and prefix like 
**ext2960006643_05_04_2021_12;40;57** will search a file in the provided path that match
the given prefix:

## com.opentext.qfiniti.importer.io.transformer.ExtensionPrefix2FileNameTransformer
Generates a call recording file name from a prefix, e.g. from and prefix like 
**ext2960006643_05_04_2021_12;40;57** will search a file in the provided path that match
the given prefix:

   `ext2960006643_05_04_2021_12;40;57_18638_it1483yw.wav`
   
> NOTE: This transformer has been deprecated since release 21.3. Please use `ExtensionPrefix2FileNameCachedTransformer` in his place

## com.opentext.qfiniti.importer.io.transformer.HoursMinutesSecondsToSecondsTransformer
Transforms a duration expressed as hh:mm:ss to a number of seconds

## com.opentext.qfiniti.importer.io.transformer.InteractionIdKey2FileNameTransformer
Generates a call recording file name from an id, e.g. from and Id like 2001788444D0191021 will generate a file name like:

   IRCall_2001788444D0191021.wav
   
## com.opentext.qfiniti.importer.io.transformer.LocalPartyName2TeamMemberNameTransformer
Transforms a team member name from one of these formats:

   - 1st_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
   - 1st_NAME 2nd_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
   - 1st_NAME 1st_FAMILY_NAME

to one of these:

   - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 
   - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 2nd_NAME
   - 1st_FAMILY_NAME, 1st_NAME 

## com.opentext.qfiniti.importer.io.transformer.RecordingId2FileNameTransformer
Recording Identifier to call recording .wav file name

## com.opentext.qfiniti.importer.io.transformer.RemoveHyphensTransformer
Remove Hyphens characters '-' from the input

## com.opentext.qfiniti.importer.io.transformer.UserId2UserFullNameTransformer
Transform a user id to a user full name: Last, First Middle

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```

## com.opentext.qfiniti.importer.io.transformer.UserId2UserNameTransformer
Transform a user id to a user surname.

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```

## com.opentext.qfiniti.importer.io.transformer.UserId2UserSurnameTransformer
Transform a user id to a user surname.

**NOTE:** Requires a .properties file called `user-mapping.properties`
This file looks like this:
```
93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
```
