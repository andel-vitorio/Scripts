-- Problema 01
rodaAteh :: Int -> Int -> Int
rodaAteh a b = (a + b) * 2

-- Problema 02
rodaAtehj :: Int -> Int -> Int
rodaAtehj a b 
    | (a + b) < 5 = 0
    | otherwise = (a + b) - 5

-- Problema 03
mdc :: Int -> Int -> Int
mdc a b 
    | b == 0 = a
    | otherwise = mdc b (mod a b)

-- Problema 04
mmc :: Int -> Int -> Int
mmc a b = div (a * b) (mdc a b) 

-- Problema 05
sinal :: Int -> Int
sinal a
    | a > 0 = 1
    | a < 0 = -1
    | otherwise = a

-- Problema 06
promo :: Int -> Float -> Int -> Float
promo a b c = fromIntegral(a) * b * (100.0 - fromIntegral(c)) / 100.0

-- Problema 07
troco :: Float -> Float -> Float
troco a b
    | b >= a = b - a
    | otherwise = -1.0

-- Problema 08
divisivel :: Int -> Int -> Bool
divisivel a b
    | (b /= 0) && (mod a b == 0) = True
    | otherwise = False

-- Problema 09
montaNum :: Int -> Int -> Int
montaNum a b
    | a == 1 = b
    | otherwise = montaNum (a - 1) (b * 10 + (mod b 10))
