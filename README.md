Mymonopoly
==========

Mymonopoly - opensource online monopoly game.
http://mymonopoly.net

#Technology stack:
* Spring Roo
* JPA2 (Hibernate)
* Spring (Core,MVC)
* Maven
* Twitter Bootstrap



#How to setup:

1) Copy 'infrastructure/default.properties' to file 'infrastructure/%USERNAME%.properties' and change necessary
properties. System username you can get by next actions: 'Win+R' -> type 'cmd' -> Enter -> type 'echo %USERNAME%' -> Enter.

2) Use maven to run full cycle of project assembly 'mvn clean install'.

3) Use your application server to run application from 'target' directory.

4) Test users are 'test1@test.com/admin' and 'test2@test.com/admin'.



To test a game you need to login and create a game with two players in a game room (two browsers).
Test game field can be found at '/game/mock' page.
