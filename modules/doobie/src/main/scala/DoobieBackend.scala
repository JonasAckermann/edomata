/*
 * Copyright 2021 Hossein Naderi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edomata.backend

import _root_.doobie.ConnectionIO
import _root_.doobie.FC
import _root_.doobie.Transactor
import _root_.doobie.implicits.*
import cats.data.Chain
import cats.data.EitherNec
import cats.data.NonEmptyChain
import cats.effect.Concurrent
import cats.effect.Temporal
import cats.effect.kernel.Clock
import cats.effect.kernel.Resource
import cats.implicits.*
import edomata.core.*
import fs2.Stream

import java.time.OffsetDateTime
import java.time.ZoneOffset

// final class DoobieBackend[F[_], S, E, R, N] private (
//     _journal: JournalReader[ConnectionIO, E],
//     _outbox: OutboxReader[ConnectionIO, N],
//     compiler: Compiler[F, E, N],
//     snapshot: SnapshotStore[F, S, E, R],
//     trx: Transactor[F]
// )(using m: ModelTC[S, E, R], F: Temporal[F], clock: Clock[F])
//     extends Backend[F, S, E, R, N](compiler) {
//   lazy val outbox: OutboxReader[F, N] = DoobieOutboxReader(trx, _outbox)
//   lazy val journal: JournalReader[F, E] = DoobieJournalReader(trx, _journal)
//   lazy val repository: Repository[F, S, E, R] = Repository(journal, snapshot)
// }

object DoobieBackend {

  def apply[F[_]: Concurrent](trx: Transactor[F]): Builder[F] = Builder(trx)

  final class Builder[F[_]: Concurrent](trx: Transactor[F]) {

    def build[C, S, E, R, N](
        domain: Domain[C, S, E, R, N],
        namespace: PGNamespace
    )(using
        m: ModelTC[S, E, R]
    ): F[Backend[F, S, E, R, N]] = {
      val s: F[Int] = doobie.Queries.setupSchema(namespace).run.transact(trx)

      ???
    }

  }
}
