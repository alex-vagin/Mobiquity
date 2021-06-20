# Couple of notices. 
* Clean code principles tells as that using of comments inside source code is not good practice. 
  It makes code dirty and it breaks principle: one language in one file. 
  Source code must be readable and in this case it not need to write comments. I tried to make source code more readable.
  Nevertheless comments still possible but only as exclusive case.
    
* Using checked exception is outdated and non-actual practice.
  I was forced adding UtilException class to get possibility to use Streams. 
  Without it methods which throws APIException must declare checked exception in their signature and it can't be use smothly in Streams and it makes source code less readable. Better to inherit APIException from RuntimeException.  
  
* Usually in production, currency variable declare as BigDecimal but here it's not involved in some calculations so it's possible to use double data type.
* Concerning algorithm. It's imlemented in Combination class and use kind of tree algorithm where in each level all possible combinations are ceneraned. On first level - combinations with one element, on second with two ones and so on. It's recursive algorithm. The recursion breaks in two cases:
  * Size of Combinations list is less than source Items list on 1. Case when Combinations and source Items are equal checks in Pack.calculate();
  * if weight of Combinations more than weight limit of package
* JUnit tests checks algorithm correctness and various cases when input data are incorrect. 
