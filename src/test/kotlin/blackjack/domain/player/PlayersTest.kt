package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {

    @DisplayName("플레이어를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = ["aa, bb"])
    fun createPlayers(input: String) {
        assertThat(Players.getOrNull(input)!!.participants).contains(
            Player(0, "aa"), Player(1, "bb")
        )
    }

    @DisplayName("하나 이상의 이름이 필요하다.")
    @Test
    fun emptyPlayer() {
        assertThat(Players.getOrNull("")).isNull()
    }

    @DisplayName("가장 점수가 높은 플레이어가 우승자가 된다.")
    @Test
    fun findWinner() {
        val players = Players.getOrNull("a, b, c")!!
        players(0).receiveCard(Card(Pip.TEN, Suit.HEART))
        players(1).receiveCard(Card(Pip.NINE, Suit.HEART))
        players(2).receiveCard(Card(Pip.ACE, Suit.HEART))
        assertThat(players.findWinners()).contains(players(2))
    }

    @DisplayName("우승자는 여러명일 수 있다.")
    @Test
    fun findWinners() {
        val players = Players.getOrNull("a, b, c")!!
        players(0).receiveCard(Card(Pip.TEN, Suit.HEART))
        players(1).receiveCard(Card(Pip.NINE, Suit.HEART))
        players(2).receiveCard(Card(Pip.TEN, Suit.HEART))
        assertThat(players.findWinners()).contains(players(0), players(2))
    }
}
