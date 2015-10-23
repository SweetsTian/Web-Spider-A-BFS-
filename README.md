# Web-Spider-AI
Keyword filtering, automatic search, Data mining and information extraction. Java as programming language. Employed Fuzzy logic, A* and BFS algorithm.


When run the fuzzy.jar, please sure the "jar folder" and the "fuzzy.jar" in the same folder. Because I used a third part jar to solve the search problems. Otherwise it can not compile.

Workflow:
1.Read the user input, the url and keywrods.Process the keywords string.

First Change string to lowercase. According positive(e.g. "and") and negative(e.g. "no" "not") words to divide the keywords to tow parts.
(e.g. CHANGE "Computer, and keyboard not DISK    and phone!" TO "computer, and keyboard" and "not disk    and phone!")

Second remove punctuation and long space.
(e.g. CHANGE "computer, and keyboard" and "disk    and phone!" TO "computer and keyboard" and "not disk and phone")

Third remove stopwords(e.g. "and", "you", "no" ).
(e.g. CHANGE "computer and keyboard" and "not disk and phone" TO "computer keyboard" and "disk phone")

Fourth add them to items list.


2. Analyse the input url, initialize relevant parameter.
Initialize Rate: to determine appropriate score of the target page. Calculate by jfuzzyLogic, inputs are base score(the score of the input url), number of positive items and negative items. 

Initialize Expect Target: Before analyse a url, get expect score of the page and compare with the expect target, if former is bigger, then add the page will anaylse. Calculate by jfuzzyLogic, inputs is base score. 

Add children page.

3. Analyse url(A star algorithm)
There are three important queue. ResultQueue is the open queue of the algorithm, get the first page of the queue(Is the highest score page). And add the page to the AStarCloseQueue. Then get children page then Calculate the expect score(Calculate by jfuzzyLogic, inputs are the number of parent page's score divide children's size and key words items of the link's herf.) of each child if expect score bigger than the Expect Target then add the child to TaskQueue(Benefit: save time and space and the parent's score and herf text is also an important factor to find the target page).

Thread get task from the TaskQueue to count the score(depends on the frequency of the key words items appearance in the page) of the page and add it to the ResultQueue. 

Then do the former step until find the target page.

3. Analyse url(BFS algorithm)
Compare with the A star algorithm, the order of analyse page and save result is Breadth-First. Thread get task form TaskQueue and save result to ResultQueue.

4. Terminal requirement
If a search page's score is bigger than Rate*base score. Terminate search.
OR If in a period of time("timeout" default is 30 seconds) can not find it, then return the page which score is highest.
The WebSpider interface provide a overload method to set the number of timeout.
Print the result.


Features:
Simple semantic analysis.
Use two algorithm.
Thread Pool and expect judge save time and space.














