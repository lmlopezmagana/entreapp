import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { token } from '../../services/passport'
import { create, index, show, update, destroy, showTranslated, VisitPoi, serveQrAsImg, existsUniqueName, savePoiAsFav, deletePoiAsFav, getFavorites, getVisited } from './controller'
import { schema } from './model'
export Poi, { schema } from './model'

const router = new Router()
const { name, categories, loc, uniqueName, qrCode, audioguides, description, coverImage, images, year, creator, status, schedule, price } = schema.tree

/**
 * @api {post} /pois Create poi
 * @apiName CreatePoi
 * @apiGroup Poi
 * @apiPermission admin
 * @apiParam {String} access_token admin access token.
 * @apiParam name Poi's name.
 * @apiParam categories Categories's ids.
 * @apiParam loc Poi's coordinates.
 * @apiParam uniqueName Poi's Unique Name.
 * @apiParam qrCode Poi's qrCode.
 * @apiParam audioguides Poi's audioguides.
 * @apiParam description Poi's description.
 * @apiParam coverImage Poi's images.
 * @apiParam images Poi's images.
 * @apiParam year Poi's year.
 * @apiParam creator Poi's creator.
 * @apiParam status Poi's status.
 * @apiParam schedule Poi's schedule.
 * @apiParam price Poi's price.
 * @apiSuccess {Object} poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 admin access only.
 */
router.post('/',
  token({ required: true, roles: ['admin'] }),
  // body({ name, categories, loc, qrCode, audioguides, description, coverImage, images, year, creator, status, schedule, price }),
  body({ name, categories, loc, uniqueName, qrCode, audioguides, description, coverImage, images, year, creator, status, schedule, price }),
  create)

  /**
 * @api {get} /qr/:uniqueName Generate Poi's QR Code
 * @apiName GenerateQR
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiParam {String} uniqueName poi's unique name.
 * @apiSuccess {String} qrCode Poi's QR code.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.get('/pois/qr/:uniqueName',
  token({ required: true }),
  serveQrAsImg
)

  /**
 * @api {get} /qr/:uniqueName Check if  a poi exists with this Unique Name
 * @apiName CheckUniqueName
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiParam {String} uniqueName poi's unique name.
 * @apiSuccess (Success 200) poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.get('/pois/exists/:uniqueName',
  token({ required: true }),
  existsUniqueName
)
 /**
 * @api {put} /fav/add/:id Add a Poi to user's favourite list
 * @apiName AddPoiFavourite
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiParam {String} id poi's id.
 * @apiSuccess (Success 200) poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.put('/pois/fav/add/:id',
  token({ required: true }),
  savePoiAsFav
)

/**
 * @api {put} /fav/del/:id Delete a Poi to user's favourite list
 * @apiName DeletePoiFavourite
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiParam {String} id poi's id.
 * @apiSuccess (Success 200) poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.put('/pois/fav/del/:id',
  token({ required: true }),
  deletePoiAsFav
)

/**
 * @api {get} /pois/favs Retrieve  user's favourite pois list
 * @apiName RetrieveFavourites
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of pois.
 * @apiSuccess @apiSuccess {Object[]} rows List of pois.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 *@apiError 401 user access only.
 */
router.get('/favs',
  token({ required: true }),
  getFavorites
)

/**
 * @api {get} /pois/visited Retrieve  user's visited pois list
 * @apiName RetrieveVisited
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of pois.
 * @apiSuccess @apiSuccess {Object[]} rows List of pois.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 401 user access only.
 */
router.get('/visited',
  token({ required: true }),
  getVisited
)

/**
 * @api {get} /pois Retrieve pois
 * @apiName RetrievePois
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of pois.
 * @apiSuccess {Object[]} rows List of pois.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 401 user access only.
 */
router.get('/',
  token({ required: true }),
  query({
    near: { paths: 'loc.coordinates' }
  }, {
    near: true, distanceField: 'loc.distance'
  }),
  index)

/**
* @api {get} /pois/:id Retrieve translated Poi
* @apiName RetrieveTranslatedPoi
* @apiGroup Poi
* @apiPermission user
* @apiParam {String} access_token user access token.
* @apiSuccess {Object} poi Poi's data.
* @apiError {Object} 400 Some parameters may contain invalid values.
* @apiError 404 Poi not found.
* @apiError 401 user access only.
*/
router.get('/:id/:idUserLanguage',
  token({ required: true }),
  showTranslated)

/**
 * @api {get} /pois/:id Retrieve poi
 * @apiName RetrievePoi
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiSuccess {Object} poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.get('/:id',
  token({ required: true }),
  show)

/**
 * @api {put} /pois/:id Update poi
 * @apiName UpdatePoi
 * @apiGroup Poi
 * @apiPermission admin
 * @apiParam {String} access_token admin access token.
 * @apiParam name Poi's name.
 * @apiParam categories Categories's ids.
 * @apiParam coordinates Poi's coordinates.
 * @apiParam qrCode Poi's qrCode.
 * @apiParam audioguides Poi's audioguides.
 * @apiParam description Poi's description.
 * @apiParam coverImage Poi's images.
 * @apiParam images Poi's images.
 * @apiParam year Poi's year.
 * @apiParam creator Poi's creator.
 * @apiParam status Poi's status.
 * @apiParam schedule Poi's schedule.
 * @apiParam price Poi's price.
 * @apiSuccess {Object} poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 admin access only.
 */
router.put('/:id',
  token({ required: true, roles: ['admin', 'contributor'] }),
  body({ name, categories, loc, qrCode, audioguides, description, coverImage, images, year, creator, status, schedule, price }),
  update)

/**
 * @api {delete} /pois/:id Delete poi
 * @apiName DeletePoi
 * @apiGroup Poi
 * @apiPermission admin
 * @apiParam {String} access_token admin access token.
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Poi not found.
 * @apiError 401 admin access only.
 */
router.delete('/:id',
  token({ required: true, roles: ['admin'] }),
  destroy)

/**
 * @api {put} /pois/visit/:uniqueName Update userVisited, userBadges and retrieve Poi
 * @apiName RetrievePoi
 * @apiGroup Poi
 * @apiPermission user
 * @apiParam {String} access_token user access token.
 * @apiSuccess {Object} poi Poi's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Poi not found.
 * @apiError 401 user access only.
 */
router.put('/visit/:uniqueName',
  token({ required: true }),
  VisitPoi)

export default router
