
Thymeleaf Eclipse Plugin
========================

A plugin for the Eclipse IDE to add auto-completion support for the Thymeleaf
standard dialect processors in the Eclipse Web Tools Platform HTML source
editor.

 - Current version: 0.2.0
 - Released: 13 January 2013

Note that this plugin is still _very_ beta, so I welcome any feedback and/or bug
reports, either through the Thymeleaf forum (on this thread: http://forum.thymeleaf.org/Thymeleaf-content-assist-plugin-for-Eclipse-td4025498.html),
or as issue reports through the GitHub page.


Minimum Requirements
--------------------

 - Java 6
 - Eclipse Indigo SR2 (3.7.2) w/ Web Tools Platform 3.3.2 (ie: the Java EE
   Developer bundle)


Installation
------------

Download the JAR from the Thymeleaf forum thread (http://forum.thymeleaf.org/Thymeleaf-content-assist-plugin-for-Eclipse-td4025498.html)
and place it in your Eclipse /dropins or /plugins folder.


Usage
-----

This plugin works in Eclipse WTP's HTML source editor, so you should open your
HTML files with that, rather than a plain-text or other XML editor.

In any HTML file, add the Thymeleaf prefix and namespace declaration to it, eg:

	<!doctype html>
	<html xmlns:th="http://www.thymeleaf.org">

Then, start using the content assist menu wherever a Thymeleaf attribute
processor could go (usually by CTRL+SPACE inside an HTML element). 


Changelog
---------

### 0.2.0
 - Added Eclipse API baseline support to work towards other versions of Eclipse.
 - Resolved [Issue #1](thymeleaf-eclipse-plugin/issues/1) so the plugin can now
   work on Java 6, and Eclipse 3.7.2 w/ WTP 3.3.2.
 - Fixed some spelling mistakes in the standard attribute processor suggestions,
   which would insert misspelled processors into your code!  Whoops!
 - Started work on resolving [Issue #2](thymeleaf-eclipse-plugin/issues/1) by
   adding some documentation to many of the standard attribute processors.
   
### 0.1.0
 - Initial release

