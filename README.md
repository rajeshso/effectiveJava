Arrays.toString(anArray) => [4, 2]
Arrays.copyOfRange(result, 0, 2); => [4, 2,0,0,0] => [4, 2] //note: endIndex is exclusiveIndex

// copy array without specified element
int[] array= { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
int[] array2 = Arrays.stream( array ).filter( value -> value != 6 ).toArray();
// Outputs: [1, 2, 3, 4, 5, 7, 8, 9]

Arrays.sort(anArray) //Note the original anArray gets rearranged in the sort
//Max and Min
Integer[] num = { 2, 4, 7, 5, 9 };
int max1 = Arrays.stream(a).max().getAsInt();
int min1 = Arrays.stream(a).min().getAsInt();
// using Collections.min() to find minimum element using only 1 line.
int min = Collections.min(Arrays.asList(num));
// using Collections.max() to find maximum element using only 1 line.
int max = Collections.max(Arrays.asList(num));

//sum
Arrays.stream(a).sum()
