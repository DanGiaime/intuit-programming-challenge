# intuit-programming-challenge
Intuit@RIT programming challenge attempt. Keeping people together longer through their purchases.

All that is needed to run this solution is the transaction data folder with the 3 executable .jars in it.

First, download the transaction-data folder found in rit-challenge-master.

Second, navigate to this folder using a command prompt of sorts.

Third, run each .jar, in order, using the following commands.

	java -jar TraitClassifier.jar

	java -jar PurchaseSimplifier.jar

	java -jar WordToVec.jar

After each of these, you should see new adjusted files pop up in the folder.

--------------------------------------------------------------------------------------------------

The TraitClassifier.jar will classify each of the transactions based on where they occured. Some will be classified as "Artsy", while some may be telling of "No-car".

--------------------------------------------------------------------------------------------------

One these are classified, PurchaseSimplifier.jar will total these transactions across each class. One this is done, it is easy to see what kind of person we are dealing with. If there are mostly Artsy purchases, they're an artistic kind of person. If there are moreso Athletic purchases, they're an athletic kind of person. etc, etc.

--------------------------------------------------------------------------------------------------

Once we've summarized and classified their purchases, WordToVec.jar will compare each person against every other person, and determine the closest match for each given person. So, for a user-X, they will be matched to a user-Y, who most closesly resembles themselves. We use the intution that similar people make the best matches here for convenience.


This comparison is made by vectorizing the purchases made by each person, using a "bag-of-words"-esque method. However in this case, the "bag" holds the classifiers of transactions. So, we effectively create a vector saying what % of a user's purchases were Artsy, implied No-car, implied student, etc. We then find the distances between this vector and every other user vector, to determine the best match for a given user.


If two users match to each other, the program will output "Perfect match!" next to the match listing. In this way, we take the transactions of a few people over time, and show them the similarities between themselves and others, so they might have a chance at love.

--------------------------------------------------------------------------------------------------

Things I wish I'd had time to do:

- Implement K-means for personality clustering

Had there been more time, I could've run K-means on the user data to try to find clusters of users who were very similar, which could define "personalities", giving simpler but still in depth classification to each user.

- Look into neural network to see what features are most significant

I considered implementing a neural network with polynomial features, accounting for the intuition that certain features, such as Artsy and Coffee, might go together frequently, leading to Artsy*Coffee being a very useful feature. Sadly, I did not have time to pursue this.

