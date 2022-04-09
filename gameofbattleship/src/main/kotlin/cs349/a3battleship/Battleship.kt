package cs349.a3battleship

import cs349.a3battleship.model.*
import cs349.a3battleship.model.ships.ShipType
import cs349.a3battleship.ui.ViewOpponent
import cs349.a3battleship.ui.ViewPlayer
import cs349.a3battleship.ui.ViewMid
import javafx.application.Application
import javafx.geometry.HorizontalDirection
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage

class Battleship : Application() {
    override fun start(stage: Stage) {

        var model = Game(10, true)
        var computer = AI(model)
        //var human = Human(model)

        var player1 = Player.Human
        var player2 = Player.AI
        //model.onPlayerSetupBegin[2]
        //model.onPlayerSetupBegin.add { player1 }
        //model.onPlayerSetupBegin.add { player2 }
        var playerBoard = Board(10)
        var opponentBoard = Board(10)



        //paneMain.background = Background(BackgroundFill(javafx.scene.paint.Color.LIGHTBLUE, null, null))











//        if(model.getShipsPlacedCount(player1) == 5) {
//            println("now we can start the game as all 5 ships are placed")
//        }

        /*
        println("attacked cells of player 1 are ${model.getAttackedCells(player1)}")
        println("attacked cells of player 2 are ${model.getAttackedCells(player2)}")



        println("at the top of players board 1")
        //println(model.getBoard(player1))
        //model.getBoard(player1)
        for(i in 0..9) {
            for (j in 0..9) {
                print(model.getBoard(player1)[i][j])
            }
            println()
        }
        println("at the bottom of players board 1")

        println("at the top of players board 2")
        //println(model.getBoard(player1))
        //model.getBoard(player1)
        for(i in 0..9) {
            for (j in 0..9) {
                print(model.getBoard(player2)[i][j])
            }
            println()
        }
        println("at the bottom of players board 2")
        */

//        model.placeShip(player1, ShipType.Destroyer, cs349.a3battleship.model.Orientation.VERTICAL, Cell(0,0))
//        model.placeShip(player1, ShipType.Cruiser, cs349.a3battleship.model.Orientation.VERTICAL, Cell(1,1))
//        model.removeShip(player1, Cell(1,1))
//        model.placeShip(player1, ShipType.Submarine, cs349.a3battleship.model.Orientation.VERTICAL, Cell(5,5))
//
//        println(model.getShipsPlacedCount(player1))
//        println(model.getShipsToPlace())
//
//        println("here is where i am babe")
//
//        model.attackCell(Cell(5,5, true))
//
//
//        println(model.getAttackedCells(player1))
//
//
//        println("at the top of players board 2")
//        //println(model.getBoard(player1))
//        //model.getBoard(player1)
//        for(i in 0..9) {
//            for (j in 0..9) {
//                print(model.getBoard(player1)[i][j])
//            }
//            println()
//        }
//        println("at the bottom of players board 2")


        while(model.getShipsPlacedCount(player1) < 5) {
            model.placeShip(player1, ShipType.Destroyer, cs349.a3battleship.model.Orientation.VERTICAL, Cell(0,0))
            model.placeShip(player1, ShipType.Cruiser, cs349.a3battleship.model.Orientation.VERTICAL, Cell(1,1))
            model.placeShip(player1, ShipType.Submarine, cs349.a3battleship.model.Orientation.VERTICAL, Cell(2,2))
            model.placeShip(player1, ShipType.Battleship, cs349.a3battleship.model.Orientation.VERTICAL, Cell(3,3))
            model.placeShip(player1, ShipType.Carrier, cs349.a3battleship.model.Orientation.VERTICAL, Cell(4,4))
        }

        model.startGame()
        println("we are out here yessss")

//        while(/*humanwon or aiwon*/true) {
//            model.attackCell(Cell(5,5, true))
//            model.attackCell(Cell(5,4, true))
//            model.attackCell(Cell(5,6, true))
//        }
        //while(someoneWon(model))
        var letBreak = 0
        for(i in 0..9) {
            for(j in 0..9) {
                model.attackCell(Cell(j,i, true))

                if(someoneWon(model) == 1) {
                    println("Human won")
                    letBreak = 1
                    break
                } else if (someoneWon(model) == 2) {
                    println("Computer won")

                    letBreak = 1
                    break
                } else {
                    //nobody won
                }
            }
            if(letBreak == 1) {
                break
            }
        }





//        model.attackCell(Cell(5,3, true))
//        model.attackCell(Cell(5,5, true))
//        model.attackCell(Cell(5,4, true))
//        model.attackCell(Cell(5,6, true))
//        model.attackCell(Cell(5,7, true))
//        model.attackCell(Cell(5,8, true))
//        model.attackCell(Cell(5,9, true))




        var viewPlayer = ViewPlayer(model)
        var viewOpponent = ViewOpponent(model)
        var viewMid = ViewMid(model)
        val mainHBox = HBox()
        mainHBox.children.addAll(viewPlayer, viewMid, viewOpponent)
        val scene = Scene(mainHBox)
        stage.scene = scene

        // stage.scene = ...
        stage.title = "A3 Battleship (a227kuma)"
        stage.width = 875.0
        stage.height = 400.0
        stage.isResizable = false
        stage.show()
    }


    private fun someoneWon(model: Game): Int? {
        //return 1 if human won
        //return 2 if computer won
        //return 0 if nobody won
        var countHuman = 0
        var countComputer = 0
        for(i in 0..9) {
            for (j in 0..9) {
                if (model.getBoard(Player.Human)[i][j] == CellState.ShipSunk) {
                    countHuman += 1
                }
                if(model.getBoard(Player.AI)[i][j] == CellState.ShipSunk) {
                    countComputer += 1
                }
            }
        }

        return if(countHuman == 17) {
            2
        } else if (countComputer == 17) {
            1
        } else {
            0
        }
    }
}