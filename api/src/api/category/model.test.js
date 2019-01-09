import { Category } from '.'

let category

beforeEach(async () => {
  category = await Category.create({ name: 'test', supercategory: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = category.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(category.id)
    expect(view.name).toBe(category.name)
    expect(view.supercategory).toBe(category.supercategory)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = category.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(category.id)
    expect(view.name).toBe(category.name)
    expect(view.supercategory).toBe(category.supercategory)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
