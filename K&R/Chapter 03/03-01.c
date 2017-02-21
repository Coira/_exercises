#include <stdio.h>

int binsearch(int x, int v[], int n);

int main()
{
  int arr[20] = {0,4,8,10,13,15,18,21,24,26,29,33,37,39,40,49,55,58,66,73};
  printf("%i\n", binsearch(200,arr,20));
  printf("%i\n", binsearch(0,arr,20));
  printf("%i\n", binsearch(39,arr,20));
  printf("%i\n", binsearch(73,arr,20));
  return 0;
}

int binsearch(int x, int v[], int n)
{
  int low, high, mid;

  low = 0;
  high = n - 1;
  mid = (low+high) / 2;
  while (low <= high) {
    
    if (x < v[mid])
      high = mid - 1;
    else
      low = mid + 1;

    mid = (low+high) / 2;
  }

  if (v[mid] == x) return mid;
  return -1;
}

    
