{
	"$schema": "http://json-schema.org/draft-07/schema#",
	"type": "object",
	"properties": {
		"tables": {
			"type": "array",
			"minItems": 1,
			"uniqueItems": true,
			"items": {
				"type": "object",
				"properties": {
					"title": {
						"type": "string"
					},
					"code": {
						"type": "string",
						"minLength": 1
					},
					"attributes": {
						"type": "array",
						"minItems": 1,
						"items": {
							"type": "object",
							"properties": {
										"title": {
											"type": "string",
											"minLength": 1
										},
										"code": {
											"type": "string",
											"minLength": 1											
										},
										"isPK": {
											"type": "boolean"
										},
										"isRequired": {
											"type": "boolean"
										}
							},
							"required": ["title", "code", "isPK", "isRequired"],
							"oneOf": [
								{
									"properties": {
										"type": {
											"enum": ["string"]
										},
										
										"maxLength": {
											"type": "integer",
											"minimum": 0
										}
									},
									"required": [
										"type",
										"maxLength"
									]
								},
								{
									"properties": {
										"type": {
											"enum": ["number"]
										},
										"maxLength": {
											"type": "integer",
											"minimum": 0
										}
									},
									"required": [
										"type",
										"maxLength"
									]
								},
								{
									"properties": {
										"type": {
											"enum": ["boolean"]
										}
									},
									"required": [
										"type"
									],
								}
							]
						}
					}
				},
				"required": [
					"title",
					"code",
					"attributes"
				],
				"additionalProperties": false
			}
		},
		"relations": {
			"type": "array",
			"uniqueItems": true,
			"items": {
				"type": "object",
				"properties": {
					"title": {
						"type": "string",
						"minLength": 1
					},
					"code": {
						"type": "string",
						"minLength": 1
					},
					"source": {
						"type": "string"
					},
					"destination": {
						"type": "string"
					},
					"keysSource": {
						"type": "array",
						"items": {
							"type": "string"
						},
						"minItems": 1
					},
					"keysDestination": {
						"type": "array",
						"items": {
							"type": "string"
						},
						"minItems": 1
					}
				},
				"required": [
					"title",
					"code",
					"source",
					"destination",
					"keysSource",
					"keysDestination"
				],
				"additionalProperties": false
			}
		}
	},
	"required": [
		"tables",
		"relations"
	]
}