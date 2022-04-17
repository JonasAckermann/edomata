package edomata.backend

import cats.data.NonEmptyChain
import edomata.core.*
import fs2.Stream

import java.time.OffsetDateTime
import java.util.UUID

trait OutboxReader[F[_], N] {
  def read: Stream[F, OutboxItem[N]]
  def markAllAsSent(items: NonEmptyChain[OutboxItem[N]]): F[Unit]
  def markAsSent(item: OutboxItem[N], others: OutboxItem[N]*): F[Unit] =
    markAllAsSent(NonEmptyChain.of(item, others: _*))
}

final case class OutboxItem[N](
    seqNr: SeqNr,
    time: OffsetDateTime,
    data: N,
    metadata: MessageMetadata
)
