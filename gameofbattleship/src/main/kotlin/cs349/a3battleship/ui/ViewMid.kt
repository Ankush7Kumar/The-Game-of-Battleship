package cs349.a3battleship.ui

import cs349.a3battleship.ui.MovableManager

import cs349.a3battleship.model.Game
import cs349.a3battleship.ui.IView
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Circle
import javafx.stage.Stage
import kotlin.random.Random

internal class ViewMid(
    private val model: Game
): BorderPane() , IView {

//    private lateinit var nodes: ArrayList<Shape>
//    inner class Delta {
//        var x = 0.0
//        var y = 0.0
//    }

    private val upperPane = FlowPane()
    private val label = Label("My Fleet")
    private val shipPane = Pane()

    private val buttonStartGame = Button("Start Game")
    private val buttonExitGame = Button("Exit Game")
    private val buttonPane = VBox()

    init {
        upperPane.minHeight = 25.0
        label.minHeight = 25.0
        label.font = Font.font("Verdana", FontWeight.BOLD, 16.0)
        upperPane.alignment = Pos.CENTER
        upperPane.children.add(label)
        buttonStartGame.minWidth = 150.0
        buttonExitGame.minWidth = 150.0
        buttonStartGame.isDisable = true

        buttonExitGame.addEventFilter(MouseEvent.MOUSE_CLICKED) {
            Platform.exit()
        }

        buttonPane.children.addAll(buttonStartGame, buttonExitGame)
        buttonPane.alignment = Pos.CENTER
        this.minWidth = 175.0
        this.top = upperPane


        val destroyer = Rectangle(30.0, 60.0)
        destroyer.x = 15.0
        destroyer.y = 0.0
        destroyer.fill = Color.DARKMAGENTA

        val cruiser = Rectangle(30.0, 90.0)
        cruiser.x = 45.0
        cruiser.y = 0.0
        cruiser.fill = Color.DARKCYAN

        val submarine = Rectangle(30.0, 90.0)
        submarine.x = 75.0
        submarine.y = 0.0
        submarine.fill = Color.DARKGOLDENROD

        val battleship = Rectangle(30.0, 120.0)
        battleship.x = 105.0
        battleship.y = 0.0
        battleship.fill = Color.DARKKHAKI

        val carrier = Rectangle(30.0, 150.0)
        carrier.x = 135.0
        carrier.y = 0.0
        carrier.fill = Color.DARKORANGE

        //val root = Pane()
        val mover = MovableManager(shipPane)
        mover.makeMovable(destroyer)
        mover.makeMovable(cruiser)
        mover.makeMovable(submarine)
        mover.makeMovable(battleship)
        mover.makeMovable(carrier)






//        nodes = ArrayList()
//        nodes.add(destroyer)
//        nodes.add(cruiser)
//        nodes.add(submarine)
//        nodes.add(battleship)
//        nodes.add(carrier)

//        for (s: Shape in nodes) {
//            setDragListeners(s)
//        }
//        shipPane.children.addAll(nodes)
//
//        checkShapeIntersection(nodes[nodes.size - 1])

        //shipPane.alignment = Pos.TOP_CENTER
        shipPane.children.addAll(destroyer, cruiser, submarine, battleship, carrier)
        this.center = shipPane

        buttonPane.padding = Insets(0.0,0.0,25.0,0.0)
        this.bottom = buttonPane



    }

    fun changeStartButton() {
        buttonStartGame.isDisable = !buttonStartGame.isDisable
    }

//    override fun updateView() {
//        TODO("Not yet implemented")
//    }




}