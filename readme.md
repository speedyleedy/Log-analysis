# Log Analysis

## Instructions

Given a Log file, the System will analise the file and split out some results, namely:
The top 3 IP addresses
The top 3 URLs / Paths
The number of unique IP addresses.

## Starting the application
from the root directory, run:

### linux / mac
```./logSearch.sh pathToFile```
for example:

###If the file is in the same directory
```./logSearch.sh log.txt```

###If the file is in another directory, you can use the relative or absolute path
```./run-txn.search.sh ../../../mytransactionsfile.csv```

if you have permission denied errors, type ```chmod +x run-txn-search.sh``` first,

## Developer Notes
I used the java streams collection API for this, this has an inbuilt accumulator, so it saved writing a lot of code.
I haven't done too much in streams so this took me a bit longer than expected.
Some assumptions I made for this - the Log file will always start with the IP address, and the URL will always have it's HTTP request type before it (GET, POST etc.)

Printing out the 'top 3' URLs and IPs, I don't handle ties at all, where multiple IP addresses have the same number of hits. This will just be more logic in the abstractLogSearch if this was to be handled.

I return all the results in to a map, with each result set as a key for that map, in this instance there are 2 result sets: IP addresses and URLs.
This could be refactored to return separate lists, and not a map if required, although I think that may have performance impacts on large log files, bit would make the analysis much simpler.

This all behaves in Memory - to run this over a proper server will likely require a database to save the data in, hence why I chose streams as this would be a somewhat simple replacement.
I would suggest this applicatoin isn't really used if the log file is over 100MB.

