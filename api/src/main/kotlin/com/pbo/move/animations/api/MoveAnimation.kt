package com.pbo.move.animations.api

import com.badlogic.gdx.scenes.scene2d.ui.Table
import org.pf4j.ExtensionPoint

interface MoveAnimation : ExtensionPoint {

    fun ourAttack(ourTable: Table, enemyTable: Table, totalHits: Int = 1) {}
    fun enemyAttack(ourTable: Table, enemyTable: Table, totalHits: Int = 1) {}
    fun ourAttackMissed() {}
    fun enemyAttackMissed() {}
}

interface PluggableMoveAnimation : MoveAnimation {

    fun register(animationRegisterFunction: (String, MoveAnimation) -> Unit)
}