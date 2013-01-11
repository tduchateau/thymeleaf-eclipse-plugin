
Thymeleaf Eclipse Plugin
========================

A plugin for the Eclipse IDE to add auto-completion support for the Thymeleaf
standard dialect processors in the Eclipse Web Tools Platform HTML source
editor.

 - Current version: 0.1.0, 2013/01/11, 21:30
 - Released: 11 January 2013

Note that this plugin is still _very_ beta, so I welcome any feedback and/or bug
reports, either through the Thymeleaf forum (on this thread: http://forum.thymeleaf.org/Thymeleaf-content-assist-plugin-for-Eclipse-td4025498.html),
or as issue reports through the GitHub page.


Requirements
------------

 - Java 7
 - Eclipse Juno SR1 (4.2.1) w/ Eclipse Web Tools Platform 3.4.1

The above is what I have developed and tested the plugin against, so can't
confirm it will work with other configurations.  I'll relax the requirements
once I create a plugin that actually, you know, _works_ :)


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

### 0.1.0
 - Initial release

