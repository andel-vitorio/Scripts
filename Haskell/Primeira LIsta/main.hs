ocorrencia :: Int -> [Int] -> Int
ocorrencia a [] = 0
ocorrencia a (h:t) = ocorrencia a t + if a == h then 1 else 0

unica_ocorrencia :: Int -> [Int] -> Bool
unica_ocorrencia a (h:t) = if ((ocorrencia a (h:t)) /= 1) then False else True