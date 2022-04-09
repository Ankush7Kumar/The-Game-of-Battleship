package cs349.a3battleship.ui

import cs349.a3battleship.model.CellState
import cs349.a3battleship.model.Game
import cs349.a3battleship.model.Player
import cs349.a3battleship.ui.IView
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

internal class ViewPlayer(
    private val model: Game
): VBox() , IView {

    private val label = Label("My Formation")

    private val upperPane = HBox()
    private val leftUpperSpacePane = Pane()


    private val midPane = HBox()
    private val leftPane = VBox()

    private val boardPane = GridPane()

    private val rightPane = VBox()

    private val leftLowerSpacePane = Pane()
    private val lowerPane = HBox()




    init {
        //this.background = Background(BackgroundFill(Color.LIGHTSALMON, null, null))
        this.minWidth = 350.0
        this.alignment = Pos.TOP_CENTER
        label.minHeight = 25.0
        label.font = Font.font("Verdana",FontWeight.BOLD,16.0)


        leftUpperSpacePane.minWidth = 25.0
        leftUpperSpacePane.minHeight = 25.0
        upperPane.minHeight = 25.0
        //upperPane.background = Background(BackgroundFill(Color.LIGHTYELLOW, null, null))

        upperPane.children.add(leftUpperSpacePane)
        for (i in 1..10) {
            val label = Label(i.toString())
            label.minHeight = 25.0
            label.minWidth = 30.0
            label.alignment = Pos.CENTER
            label.font = Font.font("Verdana",FontWeight.NORMAL,12.0)
            upperPane.children.add(label)
        }


        midPane.minWidth = 350.0
        midPane.minHeight = 300.0
        midPane.maxHeight = 300.0
        midPane.maxWidth = 350.0
        //midPane.background = Background(BackgroundFill(Color.LIGHTBLUE, null, null))

        leftPane.minWidth = 25.0
        for (i in 'A'..'J') {
            val label = Label(i.toString())
            label.minHeight = 30.0
            label.minWidth = 25.0
            label.alignment = Pos.CENTER
            label.font = Font.font("Verdana",FontWeight.NORMAL,12.0)
            leftPane.children.add(label)
        }

        //leftPane.background = Background(BackgroundFill(Color.LIGHTGRAY, null, null))

        boardPane.isGridLinesVisible = true
        boardPane.hgap = 1.0
        boardPane.vgap = 1.0
        boardPane.minWidth = 300.0
        boardPane.minHeight = 300.0

        for (i in 0..9) {
            for (j in 0..9) {
                val rect = Rectangle(29.0, 29.0)

                if(model.getBoard(Player.Human)[j][i] == CellState.Ocean) {
                    rect.fill = Color.LIGHTBLUE
                } else if (model.getBoard(Player.Human)[j][i] == CellState.Attacked) {
                    rect.fill = Color.DARKBLUE
                } else if (model.getBoard(Player.Human)[j][i] == CellState.ShipHit) {
                    rect.fill = Color.LIGHTSALMON
                } else {
                    rect.fill = Color.LIGHTGRAY
                }
                rect.x = i.toDouble()
                rect.y = j.toDouble()
                boardPane.add(rect, i, j)
            }
        }

        //boardPane.background = Background(BackgroundFill(Color.LIGHTBLUE, null, null))

        rightPane.minWidth = 25.0
        for (i in 'A'..'J') {
            val label = Label(i.toString())
            label.minHeight = 30.0
            label.minWidth = 25.0
            label.alignment = Pos.CENTER
            label.font = Font.font("Verdana",FontWeight.NORMAL,12.0)
            rightPane.children.add(label)
        }
        //rightPane.background = Background(BackgroundFill(Color.LIGHTCYAN, null, null))

        midPane.children.addAll(leftPane, boardPane, rightPane)


        leftLowerSpacePane.minWidth = 25.0
        leftLowerSpacePane.minHeight = 25.0
        lowerPane.minHeight = 25.0
        lowerPane.children.add(leftLowerSpacePane)
        for (i in 1..10) {
            val label = Label(i.toString())
            label.minHeight = 25.0
            label.minWidth = 30.0
            label.alignment = Pos.CENTER
            label.font = Font.font("Verdana",FontWeight.NORMAL,12.0)
            lowerPane.children.add(label)
        }
        //lowerPane.background = Background(BackgroundFill(Color.LIGHTPINK, null, null))

        children.add(label)
        children.add(upperPane)
        children.add(midPane)
        children.add(lowerPane)

    }

}