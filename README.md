# vaccine-distributor
Console application which purpose is to minimalize costs of purchasing vaccines and ensuring that each pharmacy is going to be fully supplied.
Program finds the most cost-effective purchasing strategy.

## Input data
```
# vaccine manufacturers (id | name | daily production)
0 | BioTech 2.0 | 900
1 | Eko Polska 2020 | 1300
2 | Post-Covid Sp. z o.o. | 1100
# pharmacies (id | name | daily damand)
0 | CentMedEko Centrala | 450
1 | CentMedEko 24h | 690
2 | CentMedEko Nowogrodzka | 1200
# connection between producers and pharmacies (manufacturer's id | pharmacy's id | maxium daily number of vaccines that particular manufacturer can supply to given pharmacy | vaccine's price [zł] )
0 | 0 | 800 | 70.5
0 | 1 | 600 | 70
0 | 2 | 750 | 90.99
1 | 0 | 900 | 100
1 | 1 | 600 | 80
1 | 2 | 450 | 70
2 | 0 | 900 | 80
2 | 1 | 900 | 90
2 | 2 | 300 | 100
```

## Output data
```
Eko Polska 2020 -> CentMedEko Nowogrodzka 	[koszt: 450 * 70.0 = 31500.0 zł]
 BioTech 2.0 -> CentMedEko Nowogrodzka 	[koszt: 750 * 90.99 = 68242.5 zł]
 BioTech 2.0 -> CentMedEko 24h 	[koszt: 600 * 70.0 = 42000.0 zł]
 Eko Polska 2020 -> CentMedEko 24h 	[koszt: 90 * 80.0 = 7200.0 zł]
 BioTech 2.0 -> CentMedEko Centrala 	[koszt: 300 * 70.5 = 21150.0 zł]
 Post-Covid Sp. z o.o. -> CentMedEko Centrala 	[koszt: 150 * 80.0 = 12000.0 zł]
Całkowity koszt 182092.50 zł
```
