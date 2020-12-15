
public static class Modelankylosaurus extends EntityModel {
	private final RendererModel tail1;
	private final RendererModel tail2;
	private final RendererModel tail3;
	private final RendererModel body;
	private final RendererModel rightlegs;
	private final RendererModel leftlegs;
	private final RendererModel head;

	public Modelankylosaurus() {
		textureWidth = 128;
		textureHeight = 128;

		tail1 = new RendererModel(this);
		tail1.setRotationPoint(0.0F, 15.75F, 29.0F);
		tail1.cubeList.add(new ModelBox(tail1, 0, 36, -1.5F, -1.75F, 0.0F, 3, 3, 6, 0.0F, false));

		tail2 = new RendererModel(this);
		tail2.setRotationPoint(0.0F, -0.25F, 6.0F);
		tail1.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 12, 22, -1.5F, -1.5F, 0.0F, 3, 3, 6, 0.0F, false));

		tail3 = new RendererModel(this);
		tail3.setRotationPoint(0.0F, 0.0F, 6.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 0, 19, -1.5F, -1.5F, 0.0F, 3, 3, 6, 0.0F, false));
		tail3.cubeList.add(new ModelBox(tail3, 0, 0, -4.0F, -2.0F, 6.0F, 8, 4, 5, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 10.0F, 2.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -13.0F, 3.0F, -4.0F, 26, 5, 31, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 36, -11.0F, 0.0F, -2.0F, 22, 3, 27, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 66, -9.0F, -1.0F, 0.0F, 18, 1, 23, 0.0F, false));

		rightlegs = new RendererModel(this);
		rightlegs.setRotationPoint(-9.0F, 24.0F, 25.0F);
		rightlegs.cubeList.add(new ModelBox(rightlegs, 0, 66, -2.0F, -6.0F, -26.0F, 3, 6, 3, 0.0F, false));
		rightlegs.cubeList.add(new ModelBox(rightlegs, 13, 48, -2.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		leftlegs = new RendererModel(this);
		leftlegs.setRotationPoint(9.0F, 24.0F, 25.0F);
		leftlegs.cubeList.add(new ModelBox(leftlegs, 59, 66, -1.0F, -6.0F, -26.0F, 3, 6, 3, 0.0F, false));
		leftlegs.cubeList.add(new ModelBox(leftlegs, 0, 51, -1.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		head = new RendererModel(this);
		head.setRotationPoint(-0.5F, 15.5F, -2.15F);
		head.cubeList.add(new ModelBox(head, 0, 45, -2.5F, -1.5F, -2.85F, 5, 3, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 9, -3.0F, -2.0F, -8.85F, 6, 4, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		tail1.render(f5);
		body.render(f5);
		rightlegs.render(f5);
		leftlegs.render(f5);
		head.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}