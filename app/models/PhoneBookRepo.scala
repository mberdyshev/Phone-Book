package models

import scala.concurrent._
import javax.inject._
import play.api.db.slick._
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

class PhoneBookTable(tag: Tag) extends Table[PhoneBookEntry](tag, "phonebook") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def phone = column[String]("phone", O.Unique, O.Length(15))
  def name = column[String]("name", O.Length(50))

  def * = (id.?, phone, name) <> ((PhoneBookEntry.apply _).tupled, PhoneBookEntry.unapply)
}

@Singleton
class PhoneBookRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {
  val phoneBook = TableQuery[PhoneBookTable]

  def create(entry: PhoneBookEntry): Future[Long] = db.run {
    (phoneBook returning phoneBook.map(_.id)) += entry
  }

  def get(phone: String = "", name: String = ""): Future[Seq[PhoneBookEntry]] = db.run {
    phoneBook.filter(e => e.phone.indexOf(phone) >= 0 && e.name.indexOf(name) >= 0).result
  }

  def edit(id: Long, entry: PhoneBookEntry): Future[PhoneBookEntry] = db.run {
    val newEntry = if (entry.id.isEmpty) entry.copy(id=Some(id)) else entry
    phoneBook.filter(_.id === id)
      .update(newEntry)
      .map(_ => newEntry)
  }

  def delete(id: Long): Future[Int] = db.run {
    phoneBook.filter(_.id === id).delete
  }
}
