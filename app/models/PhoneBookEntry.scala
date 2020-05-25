package models

case class PhoneBookEntry(phone: String, name: String)

object PhoneBookEntry {
  import play.api.libs.json._
  implicit val phoneBookEntryFormats: OFormat[PhoneBookEntry] = Json.format[PhoneBookEntry]
}