# AndroidMVVM
Android MVVM architecture
A sample app example of Android MVVM architecture which use an API to fetch data from server and show in a list.

App is written in java.

App uses Retrofit library for networking.

Uses "Live Data", ViewModel and Repository class

Livedata(Observabel) is an architecture component which is used to communicate b/w Repository, ViewModel and Activity class.

Repository is the only source of data. where we do our networking to fetch data from server and set it in live data.

In MainActivity we have a "setObservable()" funtion in which we access livedata from viewModel and a listener is implemented on Livedata which is notified when we get response from api and set into LiveData.

The response form the server is unexpected i.e (When response has data so it comes as Json Array, when response is null it comes as String rather then JsonArray) for this purpose i have used a "NewsResponseDeserializer" to handle unexpected json. 




