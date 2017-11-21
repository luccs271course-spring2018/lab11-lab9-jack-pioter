package edu.luc.cs271.myhashmap;

import java.util.*;

/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_TABLE_SIZE = 11; // a prime

  private List<List<Entry<K, V>>> table;

  public MyHashMap() {
    this(DEFAULT_TABLE_SIZE);
  }

  public MyHashMap(final int tableSize) {
    // allocate a table of the given size
    table = new ArrayList<>(tableSize);
    // then create an empty chain at each position
    for (int i = 0; i < tableSize; i += 1) {
      table.add(new LinkedList<>());
    }
  }

  @Override
  public int size() {
    // TODO add the sizes of all the chains
    int result = 0;

    for(int i =0; i< table.size(); i++)
    {
      result += table.get(i).size();
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsKey(final Object key) {
    // TODO follow basic approach of remove below (though this will be much simpler)
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while(iter.hasNext())
    {
      final Entry<K, V> entry = iter.next();
      if(entry.getKey() == key)
      {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean containsValue(final Object value) {
    // TODO follow basic approach of remove below (though this will be much simpler)
    int count = 0;
    for(Map.Entry<? extends K,? extends V> add : this.entrySet()) {
      if(count < this.size()) {
        if(add.getValue() == value)

        return true;
      }
      count+= 1;
    }
    return false;
  }

  @Override
  public V get(final Object key) {
    // TODO follow basic approach of remove below (though this will be simpler)
    final int index = calculateIndex(key);
    final Iterator<Entry<K,V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }
    return null;
  }

  @Override
  public V put(final K key, final V value) {
    // TODO follow basic approach of remove below (this will be similar)
    final int index = calculateIndex(key);
    final Entry<K, V> entry = new AbstractMap.SimpleEntry(key, value);
    if(table.get(index).size() == 0) {
      table.get(index).add(entry);
      return null;
    }else
    {
      Iterator<Entry<K,V>> iter = table.get(index).iterator();
      V oldValue = iter.next().getValue();
      iter.remove();

      table.get(index).add(entry);

      System.out.println(table);
      System.out.println("Here");
      return oldValue ;
    }





  }

  @Override
  public V remove(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    // TODO add each entry in m's entrySet
    int count = 0;
    for(Map.Entry<? extends K,? extends V> add : m.entrySet()) {
      if(count < m.size()) {
        System.out.println("here");
        this.put(add.getKey(), add.getValue());
      }
      count+= 1;
    }
    System.out.println(this);


  }

  @Override
  public void clear() {
    // TODO clear each chain
    for (int i = 0; i < table.size(); i ++) {
      for(int p = 0; p < table.get(i).size(); p++)
      {
        table.get(i).remove(p);
      }

    }
    System.out.println(table);



  }

  /** The resulting keySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<K> keySet() {
    final Set<K> result = new HashSet<>();
    // TODO populate the set
    for (int i=0; i <table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next().getKey());
      }
    }
    return Collections.unmodifiableSet(result);
  }

  /** The resulting values collection is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Collection<V> values() {
    final List<V> result = new LinkedList<>();
    // TODO populate the list
    for (int i=0; i <table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next().getValue());
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /** The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<Entry<K, V>> entrySet() {
    final Set<Entry<K, V>> result = new HashSet<>();
    // TODO populate the set
    for (int i=0; i <table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        result.add(iter.next());
      }
    }
    return Collections.unmodifiableSet(result);
  }

  @Override
  public String toString() {
    // TODO return the string representation of the underlying table
    for (int i=0; i <table.size(); i++) {
      final Iterator<Entry<K, V>> iter = table.get(i).iterator();
      while (iter.hasNext()) {
        Entry<K,V> entry = iter.next();
        System.out.println(entry.getKey() + " " + entry.getValue());
      }
    }
    return "";
  }

  public boolean equals(final Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Map)) {
      return false;

    } else if (this.containsKey(that) || this.containsValue(that)) {
      return true;
    }
      // TODO simply compare the entry sets
    else
      {
        Set<Entry<K,V>> test = this.entrySet();
        Set<Entry<K,V>> test2 = ((Map) that).entrySet();
        Iterator<Entry<K,V>> iter1 = test.iterator();
        Iterator<Entry<K,V>> iter2 = test2.iterator();
        int count = 0;
        if(test.size() != test2.size())
          return false;
        while(count < test.size())
        {
          Entry<K,V> tempEnt = iter1.next();
          Entry<K,V> tempEnt2 = iter2.next();
          System.out.println(tempEnt);
          System.out.println(tempEnt2);
          if(tempEnt.getValue() != tempEnt2.getValue())
          {
            return false;
          }
          if(tempEnt.getKey() != tempEnt2.getKey())
          {
            return false;
          }
          count+=1;
        }
        return true;

      }


  }

  private int calculateIndex(final Object key) {
    // positive remainder (as opposed to %)
    // required in case hashCode is negative!
    return Math.floorMod(key.hashCode(), table.size());
  }
}
