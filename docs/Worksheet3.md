What technical debt has been cleaned up
========================================
[Refactoring the database](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/90e831db7f4803b71c3b63e2d5d668eedcf82670)
The database needed a lot of refactoring this iteration to deal with the debt of
using android libraries and dependencies for it previously.  This was accidental
and prudent debt because we chose to use those libraries unaware that down the road
they would be an issue.  The main thing that arose from this is that we needed
to import the SQLite files instead of android and some methods needed to be altered
because of this.

What technical debt did you leave?
==================================

- We have a technical debt in our database when we access [email](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/blob/dev/app/src/main/java/com/group1/servicesapp/data/RealDatabase.java#L304) from the database. Only the first service is being checked from the list we obtain each time
we access it. This can be classified as prudent deliberate debt because we knew what it was doing but yet we decided to go for the best choice
at the time to deliver the product. Due to the limited time, going to change it is difficult since it ends up breaking the code.


Discuss a Feature or User Story that was cut/re-prioritized
============================================

- We decided to cut a feature that would allow users to communicate with a service provider 
in a text message format. We decided to cut this due to time constraints and the fact that 
we didn't think it fit extremely well with the idea of ordering a service. To still allow 
users to communicate with the sevice providers, we allow them to write a short description 
about their expectations for the service. We also added a contact button that will provide the 
user with the service providers email.


Acceptance test/end-to-end
==========================

The primary end-to-end test was for locating searching and browsing for services.  
I wanted to test that a user could have a type of service in mind and then easily find it
through our browsing menu.  In order to do this without it being flaky I set it up
so that the user had a clear idea of what type of service they wanted, a plumbing
service, which they wanted to locate.  The user would sign in to the default account, 
entering the exact username and password combination given to them, and search in the
searchbar for "plumbing".  Once this was done they would pick the first option on
the resulting list of services and select it, ending the acceptance test.

This was designed to have as little variability as possible and guide the user
in exactly what to do.

[Searching Acceptance Test](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/0ee886c328ee4c2dac31031763dfebf580d18f8b#448460d1d5ebbbee46da82c88c07c495a248847d_0_1)


Acceptance test, untestable
===============

I found it difficult to test the report because we didn't actually save the reports
in this iteration. Thus, I didn't know how to close the loop by checking if the report actually worked
at the end of the test.


Another difficult thing to test was the adding of a new service using the real android database.
When adding a services and accounts you cannot have duplicates of either meaning that if
we make an acceptance test to add a specific service it can only be run once on that
same device.  This makes it virtually impossible to have perminant acceptance test
for these two goals.


Velocity/teamwork
=================

[Velocity Chart](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/blob/master/velocity.jpg)

Through the developement of SkipTheService, we were generally very conservative with our estimates
because we were not sure of our capabilities as a team. From iteration 1 to 2, we bumped up our time estimates
but we also got a lot more done than expected. For iteration 3, there was still a lot we wanted to
add to the application and we were confident in our abilities to complete most of them. Due to some
issues related to Covid-19, we under-performed and had to push some features to a future iteration.
Even with some issues, we were became more accurate with our estimation as time went on.