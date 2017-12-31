# ObjectAccess.java

To store and retrieve large object, I am going to use BLOB in database to store large size object which can be access via Java language using JDBC driver. Database will be storing BLOB along with object name, size and extension in a row of table. 

The reason for choosing blob:
* Blob can handle object of any type to store.
* Using Blob to store object in database helps in making backups and sync database.
* For Blob, database simply stores a pointer to the actual data location in the table and refers to the data stored in some other location.

## Assumption:
* We already have a list containing path of the object in our file system i.e., 10 objects in the form of file. 
* These objects (files) will be stored in database using BLOB, the method called for storing BLOB is **insertBlob(String filePath)** which will return Id (Primary Key) for the object.
* This Id will be stored in another list which can be used further to access(read) object from the database using method **readObject(int id)** which return a File object. 
* There are other methods also available to update and delete object entry from the database.


