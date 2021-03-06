# Travix Test

**Background:**

BusyFlights is a flights search solution which aggregates flight results initially from 2 different suppliers (CrazyAir and ToughJet). A future iteration (not part of the test) may add more suppliers.

# Solution:

In order to add more suppliers the following steps should be followed:

1. Create a new package with the provider's naming convention in the com.travix.medusa.busyfights.provider package, and use this package for including all of the files related to that provider.

2. Create a new interface with the provider's naming convention that extends com.travix.medusa.busyfights.provider.ProviderManagerInterface.

3. Create a new Request and a new Response with the provider's API rules.

4. Create a Spring component that implements the provider's interface created in step 2 (Check the CrazyAir and ToughJet existing implementations).

    4.1. Autowire a reference to com.travix.medusa.busyflights.manager.HttpClient.

    4.2. Implement a private method that transform a com.travix.medusa.busyflights.model.BusyFlightsRequest object into a provider's request object.

    4.3. Implement a private method that transform a provider's response object into a com.travix.medusa.busyflights.model.BusyFlightsResponse object.

    4.4. Implement getSingleResult method (and/or getList method) using the HttpClient injected instance to access the API.

5. Autowire the provider's interface into the com.travix.medusa.busyflights.manager.BusyFlightsManagerImpl component.

    5.1 Use the injected instance of the provider's component to add the call to the API in the query method after CrazyAir and ToughJet. You may use either getSingleResult or getList depending on the provider's response.

6. Add unit tests for the new classes.

7. Edit the com.travix.medusa.busyflights.BusyFlightsApplicationTests integration test to add the new provider response to the expected results.

    7.1 Mind that the result list is ordered by fare.

    7.2 Run the integration test to verify that everything is working as expected.

    7.3. Fix the bugs and run the integration test again.

# What is still missing?

1. The integration test is not working as the CrazyAir and ToughJet URIs were not provided. In order to make it work...

    1.1 The URIs should be updated on each manager.

    1.2. Provider's Request and Response objects might lack of some JSON validations.

2. Current unit tests are validating the happy path only, there is no error handling implemented yet.

3. CrazyAir's unit test is not validating the request transformation.

4. There is no unit test for ToughJet implemented yet.

# Note

More than 90 min. were used to deliver this test, due to many interruptions and obligations.

Actually... I spent more than 30 min. just for formatting this documentation file.

