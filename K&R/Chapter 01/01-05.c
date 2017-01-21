#include <stdio.h>

main()
{
  float fahr, celsius;
  int lower, upper, step;

  lower = 0;
  upper = 300;
  step = 20;

  printf("Fahrenheit\tCelsius\n");

  for (fahr = upper; fahr >= lower; fahr = fahr - step)
    printf("%5.0f %15.1f\n", fahr, (5.0/9.0) * (fahr-32));
}
