package cs349.a3battleship.ui

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.transform.Transform;

class MovableManager(parent: Node) {

    private var movingNode: Node? = null

    // the offset captured at start of drag
    private var ox = 0.0
    private var oy = 0.0
    private var isHorizontal: Boolean = false
    private val ix = parent.translateX
    private val iy = parent.translateY

    init {

        // important that this is in bubble phase, not capture phase
        parent.addEventHandler(MouseEvent.MOUSE_CLICKED) { e ->
            val node = movingNode

            if(e.button == MouseButton.SECONDARY && node != null) {
                println("rotate '$node'")
                println("e.x is '${e.x} and e.y is ${e.y}")
                //node.rotate = 90.0
                isHorizontal = !isHorizontal
                node.transforms.add(Transform.rotate(90.0, e.x, e.y))
                // we don't want to drag the background too
                e.consume()
            } else {
                if (node != null) {

                    println("drop '$node'")
                    if (isHorizontal) {
                        //node.transforms.add(Transform.rotate(90.0, e.x, e.y))
                        isHorizontal = false
                    }
                    node.translateX = ix
                    node.translateY = iy
                    movingNode = null
                }
            }


        }

        parent.addEventFilter(MouseEvent.MOUSE_MOVED) { e ->
            val node = movingNode
            if (node != null) {
                node.translateX = e.sceneX + ox
                node.translateY = e.sceneY + oy
                // we don't want to drag the background too
                e.consume()
            }
        }


    }

    fun makeMovable(node: Node) {

        node.onMouseClicked = EventHandler { e ->


            if (movingNode == null) {
                println("click '$node'")
                this.movingNode = node
                ox = node.translateX - e.sceneX
                oy = node.translateY - e.sceneY
                // we don't want to drag the background too
                e.consume()
            }
        }
    }
}