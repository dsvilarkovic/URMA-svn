{
  "tables" : [
  {
    "title": "Drzava",
    "code": "DRZ",
    "attributes": [
      {
        "title" : "Oznaka",
        "code" : "DRZ_ID",
        "type": "string",
        "maxLength": 3,
        "isRequired": true,
        "isPK" : true
      },
      {
        "title" : "Naziv",
        "code" : "DRZ_NAZIV",
        "type": "string",
        "maxLength": 128,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Glavni grad",
        "code" : "NM_PB",
        "type": "string",
        "maxLength": 5,
        "isRequired": false,
        "isPK" : false
      }
    ]
  },
  {
    "title": "Naseljeno mesto",
    "code": "NM",
    "attributes": [
      {
        "title" : "Drzava",
        "code" : "DRZ_ID",
        "type": "string",
        "maxLength": 3,
        "isRequired": true,
        "isPK" : true
      },
      {
        "title" : "Post. broj",
        "code" : "NM_PB",
        "type": "string",
        "maxLength": 5,
        "isRequired": true,
        "isPK" : true
      },
      {
        "title" : "Naziv",
        "code" : "NM_NAZIV",
        "type": "string",
        "maxLength": 128,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Broj stanovnika",
        "code" : "NM_STAN",
        "type": "number",
        "maxLength": 10,
        "isRequired": false,
        "isPK" : false
      },
      {
        "title" : "Grad",
        "code" : "NM_GRAD",
        "type": "boolean",
        "isRequired": true,
        "isPK" : false
      }
    ]
  },
  {
    "title": "Student",
    "code": "STU",
    "attributes": [
      {
        "title" : "Indeks",
        "code" : "STU_INDEKS",
        "type": "string",
        "maxLength": 15,
        "isRequired": true,
        "isPK" : true
      },
      {
        "title" : "Ime",
        "code" : "STU_IME",
        "type": "string",
        "maxLength": 64,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Prezime",
        "code" : "STU_PREZIME",
        "type": "string",
        "maxLength": 64,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Drzava rodjenja",
        "code" : "STU_DRZRODJ",
        "type": "string",
        "maxLength": 3,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Mesto rodjenja",
        "code" : "STU_MESTORODJ",
        "type": "string",
        "maxLength": 5,
        "isRequired": true,
        "isPK" : false
      },
      {
        "title" : "Drzava stanovanja",
        "code" : "STU_DRZSTAN",
        "type": "string",
        "maxLength": 3,
        "isRequired": false,
        "isPK" : false
      },
      {
        "title" : "Mesto stanovanja",
        "code" : "STU_MESTOSTAN",
        "type": "string",
        "maxLength": 5,
        "isRequired": false,
        "isPK" : false
      }
    ]
  }
  ],
  "relations" : [
    {
      "title": "Pripada drzavi",
      "code": "DRZ-NM",
      "source": "DRZ",
      "destination": "NM",
      "keysSource": [
        "DRZ_ID"
      ],
      "keysDestination": [
        "DRZ_ID"
      ]
    },
    {
      "title": "Glavni grad",
      "code": "NM-DRZ",
      "source": "NM",
      "destination": "DRZ",
      "keysSource": [
        "NM_PB"
      ],
      "keysDestination": [
        "NM_PB"
      ]
    },
    {
      "title": "Mesto rodjenja",
      "code": "NM-STU",
      "source": "NM",
      "destination": "STU",
      "keysSource": [
        "DRZ_ID", "NM_PB"
      ],
      "keysDestination": [
        "STU_DRZRODJ", "STU_MESTORODJ"
      ]
    },
    {
      "title": "Mesto stanovanja",
      "code": "NM-STU",
      "source": "NM",
      "destination": "STU",
      "keysSource": [
        "DRZ_ID", "NM_PB"
      ],
      "keysDestination": [
        "STU_DRZSTAN", "STU_MESTOSTAN"
      ]
    }
  ]
}