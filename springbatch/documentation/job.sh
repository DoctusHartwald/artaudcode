#!/bin/sh
#------------------------------------------------------------------------------
#~ @(#) Name of script : job.sh
#~ @(#) Description : HOW TO LAUNCH DIFFERENT PART OF THE DEMO PROJECT . Please see the Description section.
#~ @(#) Version : V1.0
# Auteur : Antoine ARTAUD - FRANCE
# Date de creation : 13 Mars 2010
#----------------------------------------------------------------------
# Historique des modifications :
# <Version> - <Modification> - <Auteur> - <Date> - <Description> - <COUNTRY>
# V1.0 - xx - AAD - 23/10/09 - Creation - FRANCE
#----------------------------------------------------------------------
#~ Usage : see the command line bellow
#~ Description : Case study using Spring Batch
#				 Case 1 : Hello World
#				 Case 2 : Import  CSV => Database
#				 Case 3 : Export Database => flat file
#				 Case 4 : Export Database=> XML
#				 Case 5 : Couple Spring Batch and  Velocity template.
#				 Case 6 : export our database to a Jasper Report in PDF.
#~ Entry Parameter : N/A
#~ Code retour :  N/A
#----------------------------------------------------------------------


################################################################################################################################

#------------------------------------------------------------------------------
#~ @(#) Case 1 : SimpleTaskletTestCase
#~ @(#) Description : HelloWorld in SpringBatch
#------------------------------------------------------------------------------
mvn clean test -Dtest=com.batch.simpletask.SimpleTaskletTestCase


#------------------------------------------------------------------------------
#~ @(#) Case 2 : Import  CSV => Database
#~ @(#) Description : Import a flat file to a databse .
#~ @(#) Pre-requis : create database and table ledger.sql in src/main/resources 
#------------------------------------------------------------------------------
mvn clean test -Dtest=com.batch.todb.ToDBBatchTestCase

# Or run from CommandLine batch provided by Spring Batch.
#mvn clean exec:java -Dexec.mainClass="org.springframework.batch.core.launch.support.CommandLineJobRunner"  -Dexec.args="com/batch/todb/contextToDB.xml simpleJob"


#------------------------------------------------------------------------------
#~ @(#) Case 3 : Export Database => flat file
#~ @(#) Description : We export our database to a flat file CSV.
#~ @(#) Pre-requis : create database and table ledger.sql in src/main/resources and have some data into it
#------------------------------------------------------------------------------
mvn clean test  -Dtest=com.batch.fromdb.FromDBBatchTestCase

 
#------------------------------------------------------------------------------
#~ @(#) Case 4 : Export Database=> XML
#~ @(#) Description : We export our database to a XML File using XStream framework
#~ @(#) Pre-requis : create database and table ledger.sql in src/main/resources and have some data into it
#------------------------------------------------------------------------------
mvn clean test   -Dtest=com.batch.xml.XMLProcessorTestCase

#------------------------------------------------------------------------------
#~ @(#) Case 5 : Run Velocity template
#~ @(#) Description : We export our database to a XML File using XStream framework
#~ @(#) Pre-requis : create database and table ledger.sql in src/main/resources and have some data into it
#------------------------------------------------------------------------------
mvn clean test  -Dtest=com.batch.velocity.VelocityCodeTemplateGenerator

#------------------------------------------------------------------------------
#~ @(#) Case 6 : Run Jasper template report
#~ @(#) Description : We export our database to a Jasper Report in PDF.
#~ @(#) Pre-requis : Configure InputStream input = new FileInputStream(new File("C:/workspace-PRT/springbatch/src/test/resources/com/batch/report/ReportByMemberName.xml"));
#------------------------------------------------------------------------------
mvn clean test  -Dtest=com.batch.report.RunReportsTestCase


