input <- file('stdin', 'r')
raio <- as.double(readLines(input, n = 1))

pi <- 3.14159
area <- pi * raio * raio

write(paste('A=', format(round(area, 4), nsmall = 4), sep = ''), '')