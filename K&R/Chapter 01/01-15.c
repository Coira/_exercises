#include <stdio.h>

float f_to_c(float fahr);

main()
{
  float fahr, celsius;
  int lower, upper, step;

  lower = 0;
  upper = 300;
  step = 20;

  fahr = lower;
  while (fahr <= upper) {
    printf("%3.0f %6.1f\n", fahr, f_to_c(fahr));
    fahr = fahr + step;
  }

  return 0;
}

float f_to_c(float fahr)
{
  return  5 * (fahr-32) / 9;
}
