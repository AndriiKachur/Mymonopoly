Mymonopoly
==========

Mymonopoly - opensource online monopoly game


How to setup:
1) Create database and apply dump 'infrastructure/database.sql' to it
2) Copy 'infrastructure/default.properties' to file 'infrastructure/%USERNAME%.properties' and change necessary properties
3) Use maven to run full cycle of project assembly 'mvn clean install'
4) Use your application server to run application from 'target' directory
5) Test users are test1@test.com/admin and test2@test.com/admin


To test a game you need to login and create a game with two players in a game room (two browsers).