Try using a `TreeMap` and a `HashMap` instead of `MyHashMap`.
1. Are the resulting word frequencies any different?
    The resulting word frequencies are not different.
 2. Is the time performance any different? If so, how would you rank the three implementations (in increasing order of
 time complexity)? MyHashMAP average is 498-506ms HashMap average was 520, treeMap average 580-600 the numbers are
 really close because this was run on my high end computer running the best cpu yo can get right now so not major
 difference but MyHashMap is the best

 - How are `%` and `Math.floorMod` different? Which works more reliably for computing a hash table index?
% doesn't round while floormod rounds. Math.floorMod would be the better choice for computing a hash table index.
  - What is the time complexity of `MyHashMap.size()`, and how could you make it much more efficient?
O(n) and you can change size to increase efficiency but don't make it too big
  - How does this implementation compare to one where you would directly use your linked `Node` class from the earlier
   assignment? Answer briefly in terms of ease of implementation, correctness, reliability, and performance.
   MyHashMap does increase performance but implementation is a lot more. Both have good reliability and correctness but
   Lined node class has way easier implementation