# Valid output field names

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
 