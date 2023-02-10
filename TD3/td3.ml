(* Classe de base pour les formes géométriques *)
(* Par défaut toutes les variables d'instances sont privées *)
(* Ce qui est intéressant dans les classes abstraites c'est de pouvoir *)
(* factoriser du code *)
class virtual shape =
  object (self)
    val virtual mutable name : string
    method virtual area : unit -> float
    method virtual perimeter : unit -> float
    method get_name = name
end ;;

let rect print_shape_list sl =
  match sl with
    [] -> ()
  | o::os -> Printf.printf "area : %f, perimeter : %f\n" (o#area()) (o#perimeter());
            print_shape_list os ;;

class circle r =
object
  inherit shape
  val mutable name = "circle"
  method area() = 3.14 *. r**2.
  method perimeter() = 2. *. 3.14 *. r
end ;;

class rectangle w h =
object
  inherit shape
  val mutable name = "rectangle"
  method area() = w *. h
  method perimeter() = 2. *. (w +. h)
end ;;

class square s =
object
  inherit rectangle s s
  val mutable name = "square"
end ;;

print_shape_list [new circle 3.;new rectangle 2. 6.;new square 8.] ;;

class colored c o =
object
  inherit shape
  val mutable name = o#get_name
  val decorated : shape = o
  val mutable color = c
  method area() = o#area()
  method perimeter() = o#perimeter()
  method get_name = color ^ " " ^ decorated#get_name
  method get_color = color
  method set_color c = color o c
end

let red o = new colored "red" o ;;
let blue o = new colored "blue" o ;;
List.iter (fun o -> o#print()) [red (new rectangle 3. 2.); blue (new circle 3.)] ;;