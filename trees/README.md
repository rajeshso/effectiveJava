#### CMC development Task

###### Assumptions:

Concurrency is out of scope

Price is indicated as Integer, but getCurrentPrice returns double. 
I assume that my solution would be evaluated using a test harness.
As this is an external interface, that the test harness would use, I have retained the integer and double representation.
The double returns 3 precision digits.

Lombok is used for brevity. Please enable annotation processing using this link.
https://www.baeldung.com/lombok-ide

The requirement states that the quantity will be always be fillable. If the getCurrentPrice requires a quantity that is not available,
the unavailablity is indicated as an error code -1.000.


###### Instructions to execute

The user can use the OrderHandler's createInstance to get hold of the OrderHandler interface.

An executable jar is not provided. The test harness can be directly connected with the interface to validate the solution.


  