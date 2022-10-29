**Iteration 2 Worksheet**
=======================

Paying off technical debt
---

- Made more use of the interfaces for more abstraction 
    - Originally we had quite a few cases where we made new logic and other objects directly
    instead of implementing the interfaces that we had constructed.  This resulted in us
    having to go back and change most of our constructors.  
    - [Changing to Interface](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/298874c6dae51804ec394839f66a696beec7a5c0#7aca39a4707adc1abe84d4106a167d45ce93932e_15_15)
    - This is most likely prudent and inadvertant debt we were carefull with 
    making our interfaces but didn't realize that there were more ways to use them.
    Now that we have more knowledge of how to use interfaces we can avoid this debt
    in the future.

- Refactoring of Account structure
    - In our original plan we had two separate account objects.  However we decided
    later that it would be simpler to merge these into a single account object with
    a boolean to differntiate between them.  This required changing all of our account
    and login logic.
    - [Removal of business logic](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/266ff59b1e2c1fd5beb9ce6055c460e410c323c1#758fe9c2d14c55ed848c38fd69866b917101f644_1_0)
    - We would consider this to be reckless and deliberate debt since we deliberately 
    chose to use this structure without realizing that it would result in issues
    down the road.  We simply decided we needed to start coding.


SOLID
---

Group 1 in A02 had a single responsibility violation in their logic class.  They had one class
do a variety of things with only a single class and interface.
[Violation](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-1/-/issues/20)


Retrospective
---

For iteration 1, we found that a lot of our problems came down to poor planning.
For iteration 2, we wanted to improve on our iteration planning before 
starting any work. To do this, on our first meeting we went through 
the interation 2 instructions together and planned out everything 
that needed to be down before we even thought about coding. Our main 
concern for iteration 2 was the SQL database, so we decided everything that 
needed to be done/stored for those. 

We also drastically underestimated the time that would be needed to complete 
a user story in interation 1. So for iteration 2, we increased our estimates by a small margin.

In our google doc from the first meeting, you can see we made a list of what needed
to be done for iteration 2 and the database's we needed with what they were storing.
At the bottom, we have added our retrospective from iteration 1.
[Our first meeting google doc](https://docs.google.com/document/d/1AWZ8QUF5iEPo5TL6wtMyI6k8gQBph6ZgMPQ8L79PjwA/edit?usp=sharing)

We also found in iteration that we underestimated how long the administrative 
and worksheet problems would take.  In order to remember that there was still
more work to do we made each worksheet problem an issue in gitlab.
[Worksheet Q1](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/issues/72)



Design Patterns
---

- Singleton (in database) [SQLite Database with singleton, See line 63-73](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/blob/dev/app/src/main/java/com/group1/servicesapp/data/RealDatabase.java#L63)

Iteration 1 feedback fixes
---

- [File not in a package, Issue #58](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/issues/58)  &rarr; The issue here was simply that we hadn't fully migrated away from the main activity supplied by android studio.  In this iteration we deleted this file not in a package. (https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/c037d68970104740bc4c02d14d79dbb581dbae56#16d72ce7b1b12424dd6745e71d82edf9c0530cd7_1_0)


- [if if if if, Issue #59](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/issues/59) &rarr; The problem was use of nested if statements and to fix this smell, the code was refactored by having a separate method do the validation check (62cc329800e384d27ba2b69d12f789a47a2ed363). If it does not validate then in that case it throws an exception (3e43c7f0606c6e7de54f3de1617bb87960218538).


- [Persistence in logic layer?, Issue #60](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/issues/60) &rarr;  This was a matter of a file being in the wrong place it was fixed by moving the class in question to the proper object layer. (https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/commit/c0e12ec0f578ba4155ee5666a97bf7acc8f49c7b#0be44e782e2f9b1399339476b0b874ea3f0d3477_1_1)

