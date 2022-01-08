mdc :: Int -> Int -> Int
mdc a b
 | b == 0 = a
 | otherwise = mdc b (mod a b)

mmc :: Int -> Int -> Int
mmc a b = div (a * b) (mdc a b)

isprime :: Int -> Int -> Bool
isprime a b
 | a < 2 = False
 | b > div a 2 = True
 | mod a b == 0 = False
 | otherwise = isprime a (b + 1)

primos :: [Int] -> [Int]
primos [] = []
primos (a:as)
 | (isprime a 2) == True = a:primos as
 | otherwise = primos as

pares :: [Int] -> [Int] -> [(Int, Int)]
pares [] [] = []
pares [] (b:bs) = (b, 0):(pares [] bs)
pares (a:as) [] = (1, a):(pares as [])
pares (a:as) (b:bs) = (b, a):(pares as bs)

len :: [Char] -> Int
len [] = 0
len (a:as) = 1 + len as

plus_plus :: [Char] -> [Char] -> [Char]
plus_plus [] (b) = b
plus_plus (a:as) (b) = a:plus_plus as (b)

right :: [Char] -> Int -> Int -> String
right (a:as) tam idx
 | idx < div tam 2 = a:(right as tam (idx + 1))
 | otherwise = []

left :: [Char] -> Int -> Int -> String
left [] a b = []
left (a:as) tam idx
 | idx == div tam 2 && mod tam 2 == 0 = plus_plus (left as tam (idx + 1)) [a]
 | idx > div tam 2 = plus_plus (left as tam (idx + 1)) [a]
 | otherwise = left as tam (idx + 1)

strcmp :: [Char] -> [Char] -> Bool
strcmp [] [] = True
strcmp (a:as) (b:bs)
 | a /= b = False
 | otherwise = strcmp as bs 

palind :: String -> Bool
palind str = strcmp (right str (len str) 0) (left str (len str) 0)

menosnum :: Int -> [Int] -> [Int]
menosnum a [] = [a]
menosnum a (b:bs) = (b - a):(menosnum a bs)
divisores_proprios :: Int -> Int -> [Int]
divisores_proprios num idx
 | idx == num = []
 | mod num idx == 0 = idx:(divisores_proprios num (idx + 1))
 | otherwise = divisores_proprios num (idx + 1)

sum_list :: [Int] -> Int
sum_list [] = 0
sum_list (a:as) = a + sum_list as

is_perfect :: Int -> Bool
is_perfect num
 | sum_list (divisores_proprios num 1) == num = True
 | otherwise = False

perfeito :: [Int] -> [Int]
perfeito [] = []
perfeito (a:as)
 | is_perfect a == True = a:(perfeito as)
 | otherwise = perfeito as
