type couleur = Pique | Coeur | Carreau | Trefle ;;
type carte = As of couleur 
          | Roi of couleur
          | Dame of couleur
          | Valet of couleur
          | Nombre of (int * couleur) ;;

let valeur (col:couleur) (car:carte) =
  match car with
    As _ -> 11
  | Roi _ -> 4
  | Dame _ -> 3
  | Valet c -> if c = col then 20 else 2
  | Nombre (10,_) -> 10
  | Nombre (9, c) -> if c = col then 14 else 0
  | _ -> 0 ;;

let rec val_jeu col l =
  match l with
    [] -> []
  | x::y -> (val_jeu col x) ^ (valeur_jeu col y) ;;