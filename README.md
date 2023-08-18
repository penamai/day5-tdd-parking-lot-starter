# Story1
Case 1:
Given: a parking lot, and a car
When: park the car
Then: return a parking ticket

Case 2:
Given: a parking lot, and a parking ticket
When: fetch the car
Then: return the parked car

Case 3:
Given: a parking lot with two parked cars, and two parking tickets
When: fetch the car twice
Then: return the right car with each ticket

Case 4:
Given: a parking lot, and a wrong parking ticket
When: fetch the car
Then: return no car

Case 5:
Given: a parking lot with a used parking ticket
When: fetch the car
Then: return no car

Case 6:
Given: a parking lot with maxed out capacity, and a car
When: park the car
Then: return no parking ticket

# Story2
Case 1:
Given: a parking lot, and an unrecognized ticket
When: fetch the car
Then: return nothing with error message "Unrecognized parking ticket.”

Case 2:
Given: a parking lot, and a used ticket
When: fetch the car
Then: return nothing with error message "Unrecognized parking ticket."

Case 3:
Given: a parking lot without any position, and a car
When: park the car
Then: return nothing with error message "No available position."

# Story3
Case 1:
Given: a parking lot, a standard parking boy, and a car
When: park the car
Then: return a parking ticket.

Case 2:
Given: a parking lot with a parked car, a standard parking boy, and a parking ticket
When: fetch the car
Then: return the parked car.

Case 3:
Given: a parking lot with two parked cars, a standard parking boy, and two parking tickets
When: fetch the car twice
Then: return the right car with each ticket

Case 4:
Given: a parking lot, a standard parking boy, and a wrong parking ticket
When: fetch the car
Then: return nothing with error message "Unrecognized parking ticket.”

Case 5:
Given: a parking lot, a standard parking boy, and a used parking ticket
When: fetch the car
Then: return nothing with error message "Unrecognized parking ticket."

Case 6:
Given: a parking lot without any position, a standard parking boy, and a car,
When: park the car
Then: return nothing with error message "No available position."