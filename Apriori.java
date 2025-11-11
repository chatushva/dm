import java.util.*; public class Apriori {
static double minSupport = 0.3; static double minConfidence = 0.6;
public static void main(String[] args) { List<Set<String>> transactions = Arrays.asList(
new HashSet<>(Arrays.asList("Milk", "Bread", "Eggs")), new HashSet<>(Arrays.asList("Milk", "Bread")),
new HashSet<>(Arrays.asList("Milk", "Eggs")), new HashSet<>(Arrays.asList("Bread", "Eggs")),
new HashSet<>(Arrays.asList("Milk", "Bread", "Butter"))); Set<Set<String>> candidates = getCandidates(transactions); System.out.println("Frequent Itemsets:");
for (Set<String> itemset : candidates) { System.out.println(itemset);
}
}

private static Set<Set<String>> getCandidates(List<Set<String>> transactions) { Map<Set<String>, Integer> itemCount = new HashMap<>();
Set<String> allItems = new HashSet<>();
for (Set<String> t : transactions) allItems.addAll(t);

// Generate 1-itemsets
for (String item : allItems) { int count = 0;
for (Set<String> t : transactions) if (t.contains(item)) count++; if ((double) count / transactions.size() >= minSupport) {
itemCount.put(Collections.singleton(item), count);
}
}

// Generate 2-itemsets
List<String> items = new ArrayList<>(allItems); for (int i = 0; i < items.size(); i++) {
for (int j = i + 1; j < items.size(); j++) {
Set<String> pair = new HashSet<>(Arrays.asList(items.get(i), items.get(j))); int count = 0;
 
for (Set<String> t : transactions) if (t.containsAll(pair)) count++; if ((double) count / transactions.size() >= minSupport) {
itemCount.put(pair, count);
}
}
}

return itemCount.keySet();
}
}
