package tp2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Interview {
    /**
     * Finds all pairs within values which sum up to targetSum
     * @param values All possible values that can form a pair (can contain duplicates)
     * @param targetSum Pairs should add up to this
     * @return A collection containing all valid pairs with no permutations, but all combinations (empty collection if none found)
     */
    public Collection<MatchingPair> matchingPairs(Collection<Integer> values, Integer targetSum){

        HashMap<Integer,Integer> map1 = new HashMap<>();
        ArrayList<MatchingPair> listOfValue = new ArrayList<>();
        boolean repeatingValue = false;

        for(Integer it1 : values) {
            if(map1.containsKey(it1))
            {
                map1.put(it1, (map1.get(it1)+1));
                repeatingValue = true;
            }
            else {
                map1.put(it1,1);
            }
        }

        HashMap <Integer, Integer> remainElements = new HashMap<>(map1);

        for(Integer elementToRemove : map1.keySet())
        {
            if(!repeatingValue)
            {
                remainElements.remove(elementToRemove);
            }

            if(remainElements.containsKey(targetSum-elementToRemove))
            {
                if(!repeatingValue)
                {
                    listOfValue.add(new MatchingPair(elementToRemove, targetSum-elementToRemove));
                }
                else {

                    for(int i = 0; i < (map1.get(elementToRemove)*map1.get(targetSum-elementToRemove)); i++)
                    {
                        listOfValue.add(new MatchingPair(elementToRemove, targetSum-elementToRemove));
                    }
                    remainElements.remove(elementToRemove);
                }
            }


        }
        return listOfValue;
    }

}

