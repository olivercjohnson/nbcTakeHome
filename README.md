# nbcTakeHome

Solution for the take home problem stated here: https://filipluch.notion.site/filipluch/NBC-Take-home-assignment-f109112587894662a27b4fd5f417bef0

Wrote a simple, single activity, single screen application using MVVM with a repository for the 
data layer access. Jetpack compose was used to build out the views.
From the problem statement the homepage "endpoint" used a local json file in 
the /assets folder to be directly read for the response. In order to test different homepage responses
the MockNbcService can change the initial value for the homepage variant in order to given different 
responses. This was also written in a way that if we wanted to put a debug/test controller in the app
we could dynamically select variants in the app at runtime without needing to recompile & relaunching.

A small file reading interactor was used so that the NbcService interface signatures could match up
in terms of anticipating what a Retrofit interface would look like for this service, this way the 
actual production code doesn't need to slip in any direct file & json parsing reads, this way the 
mocking code is encapsulated into just mockService.
Lastly, the data layers separates the models as responses and then the classes used by the viewmodels
and views. This is to both allow for flexible parsing on the network end, easier conversion to 
helpful types such the LabelBadge enum, and a validation step where we can enforce rules on the data
before forwarding it along to the UI etc. 


Improvements: 
* The UI didn't utilize any custom theming other than the gradient background, certainly we would want to add this for anything production.
* UI dimensions were ballpark figures for the UI to roughly match the spec given in the problem statement
* The repository & viewmodel used Result<T> as its return type in order to build more extensive error handling, but other than file I/O exceptions, no errors were caught or shown in the UI, certainly this would be a place to extend further.
* Generalizing the screens would be nice extension, it appears like there is page field that would allow for us to do things such as caching page requests and could allow us to generalize from getHomepage to instead be getScreen(page: PageEnum)
