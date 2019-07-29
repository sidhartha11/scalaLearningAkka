package org.geo.sftp.example

import java.util.Properties
import java.util.Properties._
import java.io.FileInputStream
import java.io.File
import org.apache.commons.vfs2.impl.StandardFileSystemManager
import org.apache.commons.vfs2.FileSystemOptions
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder
import org.apache.commons.vfs2.Selectors
import org.apache.commons.vfs2.FileObject
import java.io.InputStream
import scala.io.Source
/**
 * https://www.mysamplecode.com/2013/06/sftp-apache-commons-file-download.html
 */
object TestSftp extends App {
  def getProperties (propfile: String): InputStream = {
    println("loading properties " + propfile)
    val s = getClass.getClassLoader.getResourceAsStream(propfile)
    println(s)
    s
  }

  def getFile = {
       
    val fileToDownload = "somefile.txt"
    val propertiesFilename = "sftp.properties"
    val props = new Properties
    
    props.load(getProperties(propertiesFilename))
    
   // props.load(new FileInputStream("properties/" + propertiesFilename))
    
    val manager = new StandardFileSystemManager;
    
    val serverAddress = props.getProperty("serverAddress").trim
    val userId = props.getProperty("userId").trim
    val password = props.getProperty("password").trim
    val remoteDirectory = props.getProperty("remoteDirectory").trim
    val localDirectory = props.getProperty("localDirectory")
    
    // initialize file manager 
    manager.init()
    
        // setup sftp configuration
    val opts = new FileSystemOptions
    SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
        opts, "no") 
        
   SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
   SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
    
   //Create the SFTP URI using the host name, userid, password,  remote path and file name
   val sftpUri = "sftp://" + userId + ":" + password +  "@" + serverAddress + "/" + 
     remoteDirectory + fileToDownload;
   
   // Create local file object
   val filepath = localDirectory +  fileToDownload;
   val file = new File(filepath);
   val localFile: FileObject = manager.resolveFile(file.getAbsolutePath());
 
   // Create remote file object
   val remoteFile: FileObject = manager.resolveFile(sftpUri, opts);
 
   // Copy local file to sftp server
   localFile.copyFrom(remoteFile, Selectors.SELECT_SELF);
   System.out.println("File download successful");
   manager.close()
  }
  def sendFile = {
//    val hostname = "ftp.georgecurington.com"
//    val username = "sidhartha11@georgecurington.com"
//    val password = "Scala123#"
//    val filter = ".*\\.html"
    
    val fileToFTP = "somefile.txt"
    val propertiesFilename = "sftp.properties"
    val props = new Properties
    
    props.load(getProperties(propertiesFilename))
    
//    props.load(new FileInputStream("properties/" + propertiesFilename))
    
    val manager = new StandardFileSystemManager;
    
    val serverAddress = props.getProperty("serverAddress").trim
    val userId = props.getProperty("userId").trim
    val password = props.getProperty("password").trim
    val remoteDirectory = props.getProperty("remoteDirectory").trim
    val localDirectory = props.getProperty("localDirectory")
    
    //check if file exists 
    val filepath = localDirectory + fileToFTP
    val file = new File(filepath)
    if ( !file.exists ) {
      throw new RuntimeException("Error. local file not found")
    }
    
    // initialize file manager 
    manager.init()
    // setup sftp configuration
    val opts = new FileSystemOptions
    SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
        opts, "no") 
   SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
   SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

   // create the sftp URI using the host name , userid , password , remote path and file
   val sftpUri = "sftp://" + userId + ":" + password  + "@" + 
   remoteDirectory + fileToFTP
   
   // Create local file object 
   val localFile = manager.resolveFile(file.getAbsolutePath())
   
   // Create remote file object 
   val remoteFile = manager.resolveFile(sftpUri, opts)
   
   // copy local file to sftp server
   remoteFile.copyFrom(localFile , Selectors.SELECT_SELF)
   manager.close
   
  }
  
  sendFile
}