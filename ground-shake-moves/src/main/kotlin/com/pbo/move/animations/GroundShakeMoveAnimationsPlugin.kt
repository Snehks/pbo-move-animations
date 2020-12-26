package com.pbo.move.animations

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.pbo.move.animations.api.MoveAnimation
import com.pbo.move.animations.api.PluggableMoveAnimation
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class GroundShakeMoveAnimationsPlugin(wrapper: PluginWrapper) : Plugin(wrapper) {

    override fun start() {
    }

    override fun stop() {
    }
}

@Extension
class GroundShakeMoveAnimations : PluggableMoveAnimation {

    override fun ourAttack(ourTable: Table, enemyTable: Table, totalHits: Int) {
        ourTable.addAction(
            Actions.sequence(
                Actions.moveBy(0F, -10f, 0.05F, Interpolation.smooth),
                Actions.moveBy(0f, 10f, 0.05F, Interpolation.smooth),
                Actions.run {
                    enemyTable.addAction(
                        createShakeAction(0.01f)
                    )
                }
            )
        )
    }

    override fun enemyAttack(ourTable: Table, enemyTable: Table, totalHits: Int) {
        ourTable.addAction(createShakeAction(0.01F))
    }

    private fun createShakeAction(durationOfShake: Float) = Actions.repeat(
        3, SequenceAction(
            Actions.parallel(
                Actions.moveBy(10f, 0f, durationOfShake, Interpolation.smooth),
                Actions.rotateBy(30f, durationOfShake, Interpolation.smooth)
            ),
            Actions.parallel(
                Actions.moveBy(-10f, 0f, durationOfShake, Interpolation.smooth),
                Actions.rotateBy(-15f, durationOfShake, Interpolation.smooth)
            ),
            Actions.parallel(
                Actions.moveBy(-10f, 0f, durationOfShake, Interpolation.smooth),
                Actions.rotateBy(-30f, durationOfShake, Interpolation.smooth)
            ),
            Actions.parallel(
                Actions.moveBy(10f, 0f, durationOfShake, Interpolation.smooth),
                Actions.rotateBy(15f, durationOfShake, Interpolation.smooth)
            )
        )
    )

    override fun register(animationRegisterFunction: (String, MoveAnimation) -> Unit) {
        animationRegisterFunction("Earthquake", this)
    }
}